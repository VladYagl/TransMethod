// Generated from D:/StudyHard/StanokParsing/Generator/src\Grammar.g4 by ANTLR 4.7
package grammar;

    import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, CONST=16, WHITESPACE=17, 
		NAME=18, CODE=19, ARGS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "CONST", "WHITESPACE", 
		"NAME", "CODE", "ARGS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'grammar'", "';'", "'attrs'", "':'", "'|'", "'*'", "'('", "')'", 
		"'@header'", "'@members'", "','", "'.'", "'<'", "'>'", "'[]'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "CONST", "WHITESPACE", "NAME", "CODE", "ARGS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u0092\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\7\21f\n\21\f\21\16\21i\13\21\3\21\3\21\3\22\6\22n\n\22\r\22"+
		"\16\22o\3\22\3\22\3\23\3\23\7\23v\n\23\f\23\16\23y\13\23\3\24\3\24\6\24"+
		"}\n\24\r\24\16\24~\3\24\5\24\u0082\n\24\7\24\u0084\n\24\f\24\16\24\u0087"+
		"\13\24\3\24\3\24\3\25\3\25\6\25\u008d\n\25\r\25\16\25\u008e\3\25\3\25"+
		"\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26\3\2\b\3\2))\5\2\13\f\17\17\"\"\5\2C"+
		"\\aac|\6\2\62;C\\aac|\4\2}}\177\177\4\2]]__\2\u0099\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t;\3\2\2\2\13"+
		"=\3\2\2\2\r?\3\2\2\2\17A\3\2\2\2\21C\3\2\2\2\23E\3\2\2\2\25M\3\2\2\2\27"+
		"V\3\2\2\2\31X\3\2\2\2\33Z\3\2\2\2\35\\\3\2\2\2\37^\3\2\2\2!a\3\2\2\2#"+
		"m\3\2\2\2%s\3\2\2\2\'z\3\2\2\2)\u008a\3\2\2\2+,\7i\2\2,-\7t\2\2-.\7c\2"+
		"\2./\7o\2\2/\60\7o\2\2\60\61\7c\2\2\61\62\7t\2\2\62\4\3\2\2\2\63\64\7"+
		"=\2\2\64\6\3\2\2\2\65\66\7c\2\2\66\67\7v\2\2\678\7v\2\289\7t\2\29:\7u"+
		"\2\2:\b\3\2\2\2;<\7<\2\2<\n\3\2\2\2=>\7~\2\2>\f\3\2\2\2?@\7,\2\2@\16\3"+
		"\2\2\2AB\7*\2\2B\20\3\2\2\2CD\7+\2\2D\22\3\2\2\2EF\7B\2\2FG\7j\2\2GH\7"+
		"g\2\2HI\7c\2\2IJ\7f\2\2JK\7g\2\2KL\7t\2\2L\24\3\2\2\2MN\7B\2\2NO\7o\2"+
		"\2OP\7g\2\2PQ\7o\2\2QR\7d\2\2RS\7g\2\2ST\7t\2\2TU\7u\2\2U\26\3\2\2\2V"+
		"W\7.\2\2W\30\3\2\2\2XY\7\60\2\2Y\32\3\2\2\2Z[\7>\2\2[\34\3\2\2\2\\]\7"+
		"@\2\2]\36\3\2\2\2^_\7]\2\2_`\7_\2\2` \3\2\2\2ag\7)\2\2bf\n\2\2\2cd\7^"+
		"\2\2df\7)\2\2eb\3\2\2\2ec\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2"+
		"\2\2ig\3\2\2\2jk\7)\2\2k\"\3\2\2\2ln\t\3\2\2ml\3\2\2\2no\3\2\2\2om\3\2"+
		"\2\2op\3\2\2\2pq\3\2\2\2qr\b\22\2\2r$\3\2\2\2sw\t\4\2\2tv\t\5\2\2ut\3"+
		"\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x&\3\2\2\2yw\3\2\2\2z\u0085\7}\2\2"+
		"{}\n\6\2\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2"+
		"\2\u0080\u0082\5\'\24\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0084\3\2\2\2\u0083|\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2"+
		"\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0089"+
		"\7\177\2\2\u0089(\3\2\2\2\u008a\u008c\7]\2\2\u008b\u008d\n\7\2\2\u008c"+
		"\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090\u0091\7_\2\2\u0091*\3\2\2\2\13\2egow~\u0081"+
		"\u0085\u008e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}