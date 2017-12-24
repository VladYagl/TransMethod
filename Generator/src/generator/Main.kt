package generator

import grammar.GrammarLexer
import grammar.GrammarParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

fun main(args: Array<String>) {
    args.forEach { fileName ->
        val grammar = GrammarParser(CommonTokenStream(GrammarLexer(CharStreams.fromFileName(fileName)))).main()
        val visitor = RuleVisitor()
        println(grammar.grammarName.text)
        grammar.grammarRule().forEach { rule ->
            println(rule.name.text + " -> " + rule.expression()?.text)
            visitor.visit(rule)
        }
        Generator(visitor.rules, rootRule = "S", grammarName = grammar.grammarName.text).generate()
    }
}
