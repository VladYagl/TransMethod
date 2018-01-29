package generator

import com.sun.org.apache.xpath.internal.operations.Bool
import generated.RegExpParser
import jdk.nashorn.internal.objects.NativeArray.forEach
import jdk.nashorn.internal.objects.NativeArray.isArray
import jdk.nashorn.internal.runtime.ScriptObject
import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.RuleContextWithAltNum
import java.io.*
import kotlin.concurrent.timer

class Generator(val rules: HashMap<String, Expression>,
                val rootRule: String,
                val grammarName: String,
                val outDir: String = "generated",
                val outPackage: String = "generated",
                val extra: HashMap<String, String> = HashMap()) {

    val LEXER_TEMPLATE = "templates/LexerTemplate.kt"
    val PARSER_TEMPLATE = "templates/ParserTemplate.kt"

    val PREDIFINED_ATTRS = setOf("text")

    private val tokens = HashMap<String, Token>()

    private fun calcTokens() {
        rules.forEach { (_, rule) ->
            if (rule !is Rule)
                if (rule is Token) {
                    tokens.put(rule.name, rule)
                } else {
                    tokens.putAll(rule.tokens.filter { it !is Rule }.map { it.name to it })
                }
        }
    }

    private fun calcFirst() {
        //Initial First
        rules.forEach { (_, expression) ->
            fun Expression.calcFirst() {
                val expr = this
                expr.choices.forEach { production ->
                    var doMore = true
                    production.list.forEach { unar ->
                        if (doMore) {
                            if (unar is Token)
                                expr.first.put(unar, production)
                            else {
                                unar.calcFirst()
                                expr.first.putAll(unar.first.map { it.key to production })
                            }
                        }

                        doMore = unar is Rule && rules[unar.ruleName]?.canBeEmpty ?: false
                    }
                }
            }
            expression.calcFirst()
        }

        //Expand rule first
        while ({
                    rules.map { (_, rule) ->
                        val toPut = HashMap<Token, Production>()
                        rule.first.forEach { (token, exp) ->
                            if (token is Rule) {
                                if (tokens.contains(token.ruleName)) {
                                    toPut.put(tokens[token.ruleName]!!, exp)
                                } else {
                                    toPut.putAll(rules[token.ruleName]!!.first.map { it.key to exp })
                                }
                            }
                        }
                        if (toPut.isNotEmpty()) {
                            val ans = toPut.map { !rule.first.contains(it.key) }.fold(false, Boolean::or)
                            rule.first.putAll(toPut)
                            ans
                        } else false
                    }.fold(false, Boolean::or)
                }()) {
        }

        //Remove rules from first
        rules.forEach { (_, rule) ->
            rule.first.filter { it.key is Rule }.forEach { token, _ -> rule.first.remove(token) }
        }
    }

    private fun calcFollow() {
        fun addFollow(ruleName: String, expression: Expression) {
            when (expression) {
                is Token -> rules[ruleName]?.follow?.add(expression)
                else -> rules[ruleName]?.follow?.addAll(expression.first.keys)
            }
        }

        fun Expression.calcLast() {
            if (this is Token) return
            val expression = this
            expression.choices.forEach { production ->
                production.list.forEachIndexed { index, unar ->
                    unar.calcLast()
                    unar.last.forEach { rule ->
                        var pos = index
                        val last = mutableSetOf(rule)
                        do {
                            pos++
                            if (pos < production.list.size) {
                                if (production.list[pos] is Rule) last.add(production.list[pos] as Rule)
                                addFollow(rule.ruleName, production.list[pos])
                            } else expression.last.addAll(last)
                        } while (pos < production.list.size && production.list[pos] is Rule && rules[(production.list[pos] as Rule).ruleName]?.canBeEmpty == true)
                    }
                }
            }
        }

        rules[rootRule]?.follow?.add(Token(name = "END", text = "$"))
        rules.forEach { (_, expression) ->
            expression.calcLast()
        }

        //Expand rules in follow
        while (rules.map { (_, expression) ->
                    if (expression.last.isEmpty()) false
                    else
                        expression.last.map {
                            val changed = rules[it.ruleName]?.follow?.containsAll(expression.follow) ?: false
                            rules[it.ruleName]?.follow?.addAll(expression.follow)
                            !changed
                        }.fold(false, Boolean::or)
                }.fold(false, Boolean::or)) {
        }

        //Replace rules with follow
        rules.forEach { _, expression ->
            expression.follow.addAll(expression.follow.map {
                rules[(it as? Rule)?.ruleName]?.first?.keys ?: HashSet()
            }.fold(HashSet()) { acc, mutableSet -> acc.union(mutableSet) as HashSet<Token> })
            expression.follow.removeIf { it is Rule }
        }
    }

    init {
        calcTokens()
        calcFirst()
        calcFollow()

        println(tokens.map { (name, token) -> name + ": " + token.text })

        println()
        rules.forEach { name, exp ->
            println(name + " -follow-> " + exp.follow.map { it.name + it.text })
        }

        println()
        rules.forEach { name, exp ->
            println(name + " -last-> " + exp.last.map { it.name + it.text })
        }

        println()
        println()
        rules.forEach { name, exp ->
            println(name + " Empty: " + exp.canBeEmpty + " -first-> " + exp.first.map { it.key.text + " to " + it.value })
        }

    }

    private val indent = "    "

    val test = "\\{(~[{}]+)}".toRegex()

    private fun formatCode(code: String, indent: String = "", braces: Boolean = false, context: Production? = null): String {
        return code
                .substring(if (!braces) {
                    1 until code.length - 1
                } else {
                    0 until code.length
                })
                .lines()
                .filter { it.isNotBlank() }
                .joinToString("\n$indent", prefix = indent) {
                    it.trim()
                            .replace("\\$[_a-zA-Z]+".toRegex()) { match -> "local.${match.value.drop(1)}" }
                            .replace("#([0-9a-zA-Z]+)\\.([_a-zA-Z]+)".toRegex()) { match ->
                                val childNumber = (if (match.groupValues[1].toIntOrNull() != null) {
                                    match.groupValues[1].toInt()
                                } else {
                                    context!!.list.indexOfFirst { it is Rule && it.ruleName == match.groupValues[1] }
                                })
                                "(local.children[$childNumber] as ${(context!!.list[childNumber] as Rule).ruleName}Node).${match.groupValues[2]}!!"
                            }
                }
    }

    private fun generateLexer() {
        BufferedReader(FileReader(LEXER_TEMPLATE)).useLines { lines ->
            val choices = tokens.map { (name, mainToken) ->
                mainToken.tokens.map { token ->
                    "${indent.repeat(3)}${token.text.replace("\'", "\"")}.first().toInt() -> ${
                    if (token.text.length - 2 > 1) {
                        "{\n${indent.repeat(4)}val text = sym.toString() + (1..${token.text.length - 3}).map { reader.read().toString() }.joinToString()" +
                                "\n${indent.repeat(4)}Token(Token.Type.$name, text)" +
                                "\n${indent.repeat(3)}}"
                    } else "Token(Token.Type.$name, sym.toChar().toString())"}"
                }.joinToString(separator = "\n")
            }.joinToString(separator = "\n")

            val pack = (if (outPackage.isNotBlank()) "package $outPackage\n\n" else "")

            val lexer = pack + lines.joinToString(separator = "\n")
                    .replace("LexerTemplate", "${grammarName}Lexer")
                    .replace("//\$types", tokens.map { indent.repeat(2) + it.key }.joinToString(separator = ",\n") + ",")
                    .replace("//\$choice", choices)

            File("$outDir/${grammarName}Lexer.kt").parentFile.mkdirs()
            BufferedWriter(FileWriter("$outDir/${grammarName}Lexer.kt")).use { writer ->
                writer.appendln(lexer)
            }
        }
    }

    private fun generateRuleParser(name: String, expression: Expression): String {
        val choices = expression.first.map { (token, production) ->
            val parseProduction = production.list.map { it ->
                when {
                    (it is Rule && tokens.contains(it.ruleName)) ->
                        "local.text += lex.token.text;lex.nextToken();local.addChild(Node(\"$it\"))"
                    it is Rule -> "local.addChild(parse${it.ruleName}(${it.callArgs}))"
                    it is Token -> "local.text += lex.token.text;lex.nextToken();local.addChild(Node(\"$it\"))"
                    else -> throw Exception("IDK HOW TO PARSE " + it.javaClass)
                }
            }.joinToString(separator = "\n${indent.repeat(4)}", prefix = indent.repeat(4))

            "Token.Type.${token.name} -> {\n" + parseProduction + (
                    if (production.code.isNotBlank())
                        "\n" + formatCode(production.code, indent = indent.repeat(4), context = production)
                    else "") +
                    "\n${indent.repeat(3)}}"
        }
        return "${indent}class ${name}Node(): Node(\"$name\") {\n" +
                expression.attrs
                        .filter { (name, _) -> !PREDIFINED_ATTRS.contains(name) }
                        .map { (name, type) -> "var $name: $type? = null" }
                        .joinToString(separator = "\n") +
                "\n$indent}\n" +
                "${indent}private fun parse$name(${expression.args}): Node {" +
                "\n${indent.repeat(2)}val local = ${name}Node()" +
                "\n${indent.repeat(2)}when (lex.token.type) {" +
                choices.joinToString(separator = "\n${indent.repeat(3)}", prefix = "\n${indent.repeat(3)}") + (
                if (!expression.canBeEmpty)
                    "\n${indent.repeat(3)}else -> throw ParseException(\"Syntax error\", lex.position)"
                else
                    " else -> {\n" + (
                            if (expression.emptyChoice?.code?.isNotBlank() == true)
                                formatCode(expression.emptyChoice.code, indent = indent.repeat(4), context = expression.emptyChoice)
                            else "") + "\n${indent.repeat(3)}}"
                ) +
                "\n${indent.repeat(2)}}" +
                "\n${indent.repeat(2)}return local" +
                "\n$indent}"
    }

    private fun generateParser() {
        BufferedReader(FileReader(PARSER_TEMPLATE)).useLines { lines ->

            val rules = rules.filter { it.value !is Token }.map { (name, expression) ->
                generateRuleParser(name, expression)
            }

            val pack = (if (outPackage.isNotBlank()) "package $outPackage\n\n" else "")

            var parser = pack + lines.joinToString(separator = "\n")
                    .replace("ParserTemplate", "${grammarName}Parser")
                    .replace("LexerTemplate", "${grammarName}Lexer")
                    .replace("Node(\"RootRule\")", "parse$rootRule()")
                    .replace("//\$rules", rules.joinToString(separator = "\n\n"))

            if (extra["header"]?.isNotEmpty() == true) {
                parser = parser.replace("//\$header", formatCode(extra["header"]!!))
            }
            if (extra["members"]?.isNotEmpty() == true) {
                parser = parser.replace("//\$members", formatCode(extra["members"]!!, indent))
            }

            File("$outDir/${grammarName}Parser.kt").parentFile.mkdirs()
            BufferedWriter(FileWriter("$outDir/${grammarName}Parser.kt")).use { writer ->
                writer.appendln(parser)
            }
        }
    }

    fun generate() {
        generateLexer()
        generateParser()
    }
}

