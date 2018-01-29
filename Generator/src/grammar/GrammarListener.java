// Generated from D:/StudyHard/StanokParsing/Generator/src\Grammar.g4 by ANTLR 4.7
package grammar;

    import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(GrammarParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(GrammarParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#grammarRule}.
	 * @param ctx the parse tree
	 */
	void enterGrammarRule(GrammarParser.GrammarRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#grammarRule}.
	 * @param ctx the parse tree
	 */
	void exitGrammarRule(GrammarParser.GrammarRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(GrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(GrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#nonterm}.
	 * @param ctx the parse tree
	 */
	void enterNonterm(GrammarParser.NontermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#nonterm}.
	 * @param ctx the parse tree
	 */
	void exitNonterm(GrammarParser.NontermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#unar}.
	 * @param ctx the parse tree
	 */
	void enterUnar(GrammarParser.UnarContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#unar}.
	 * @param ctx the parse tree
	 */
	void exitUnar(GrammarParser.UnarContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#members}.
	 * @param ctx the parse tree
	 */
	void enterMembers(GrammarParser.MembersContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#members}.
	 * @param ctx the parse tree
	 */
	void exitMembers(GrammarParser.MembersContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#argsDef}.
	 * @param ctx the parse tree
	 */
	void enterArgsDef(GrammarParser.ArgsDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#argsDef}.
	 * @param ctx the parse tree
	 */
	void exitArgsDef(GrammarParser.ArgsDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#typedArg}.
	 * @param ctx the parse tree
	 */
	void enterTypedArg(GrammarParser.TypedArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#typedArg}.
	 * @param ctx the parse tree
	 */
	void exitTypedArg(GrammarParser.TypedArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(GrammarParser.ArgsContext ctx);
}