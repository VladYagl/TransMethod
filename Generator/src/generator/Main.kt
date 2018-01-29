package generator

import grammar.GrammarLexer
import grammar.GrammarParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

fun main(args: Array<String>) {
    val rootRule = args.filterIndexed { index, _ -> index > 0 && args[index - 1] == "-root" }.first()
    val outDir = args.filterIndexed { index, _ -> index > 0 && args[index - 1] == "-dir" }.firstOrNull()
    val outPackage = args.filterIndexed { index, _ -> index > 0 && args[index - 1] == "-pack" }.firstOrNull()
    args.last().let { fileName ->
        val extra = HashMap<String, String>()

        val grammar = GrammarParser(CommonTokenStream(GrammarLexer(CharStreams.fromFileName(fileName)))).main()
        val visitor = RuleVisitor()
        println(grammar.grammarName.text)
        grammar.grammarRule().forEach { rule ->
            println(rule.name.text + " -> " + rule.expression()?.text)
            visitor.visit(rule)
        }

        extra["members"] = grammar.members()?.CODE()?.text ?: ""
        extra["header"] = grammar.header()?.CODE()?.text ?: ""

        //TODO: ROOT RULE
        Generator(
                visitor.rules,
                rootRule = rootRule,
                grammarName = grammar.grammarName.text,
                extra = extra,
                outDir = outDir ?: "generated",
                outPackage = outPackage ?: "generated").generate()
    }
}
