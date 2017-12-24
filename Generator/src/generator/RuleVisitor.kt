package generator

import grammar.GrammarBaseVisitor
import grammar.GrammarParser

class RuleVisitor : GrammarBaseVisitor<Expression>() {

    val rules = HashMap<String, Expression>()
    val follow = HashMap<String, MutableSet<Token>>()

    override fun visitGrammarRule(context: GrammarParser.GrammarRuleContext): Expression {
        if (context.expression() != null) {
            val expression = visitExpression(context.expression())
            rules[context.name.text] = expression
            return expression
        } else {
            val token = ComplexToken(context.name.text)
            context.term().forEach {
                token.add(visitTerm(it))
            }
            rules[context.name.text] = token
            return token
        }
    }

    override fun visitExpression(context: GrammarParser.ExpressionContext): Expression {
        val cases = context.nonterm().map { visitNonterm(it) }
        val expression = Expression(cases.map { it.canBeEmpty }.fold(false, Boolean::or))
        cases.forEach { expression.add(it) }
        return expression
    }

    override fun visitNonterm(context: GrammarParser.NontermContext): Production {
        if (context.unar().isEmpty()) return Production(true)
        val unars = context.unar().map { visitUnar(it) }
        val production = Production(unars.map { it.canBeEmpty }.fold(true, Boolean::and))
        production.list.addAll(unars)
        production.tokens.addAll(unars.map { it.tokens }.fold(HashSet()) { acc, mutableSet -> acc.union(mutableSet) as HashSet<Token> })
        return production
    }

    override fun visitUnar(context: GrammarParser.UnarContext?): Expression {
        return when {
            context?.ruleName != null -> Rule(context.ruleName.text)

            context?.token != null -> Token(context.token.text)

            context?.unar() != null -> visit(context.unar())

            context?.expression() != null -> visit(context.expression())

            else -> throw Exception("EMPTY, NANI!?")
        }
    }

    override fun visitTerm(context: GrammarParser.TermContext): Token {
        return Token(context.CONST().fold("") { res, const -> res + const.text })
    }
}

