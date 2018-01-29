// Generated from D:/StudyHard/StanokParsing/Generator/src\Grammar.g4 by ANTLR 4.7
package grammar;
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
		T__9=10, T__10=11, T__11=12, WHITESPACE=13, CONST=14, NAME=15, TYPE=16, 
		CODE=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "WHITESPACE", "CONST", "NAME", "TYPE", "CODE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'grammar'", "';'", "':'", "'|'", "'*'", "'('", "')'", "'@header'", 
		"'@members'", "'['", "','", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "WHITESPACE", "CONST", "NAME", "TYPE", "CODE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u008f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\6\16R\n\16\r\16\16\16S\3"+
		"\16\3\16\3\17\3\17\7\17Z\n\17\f\17\16\17]\13\17\3\17\3\17\3\20\3\20\7"+
		"\20c\n\20\f\20\16\20f\13\20\3\21\3\21\3\21\7\21k\n\21\f\21\16\21n\13\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21w\n\21\3\21\3\21\5\21{\n\21\3"+
		"\21\5\21~\n\21\3\22\3\22\6\22\u0082\n\22\r\22\16\22\u0083\3\22\5\22\u0087"+
		"\n\22\7\22\u0089\n\22\f\22\16\22\u008c\13\22\3\22\3\22\3[\2\23\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23\3\2\6\5\2\13\f\17\17\"\"\5\2C\\aac|\6\2\62;C\\aac|\4\2}}\177\177"+
		"\2\u0098\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\3%\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\13\63\3\2"+
		"\2\2\r\65\3\2\2\2\17\67\3\2\2\2\219\3\2\2\2\23A\3\2\2\2\25J\3\2\2\2\27"+
		"L\3\2\2\2\31N\3\2\2\2\33Q\3\2\2\2\35W\3\2\2\2\37`\3\2\2\2!}\3\2\2\2#\177"+
		"\3\2\2\2%&\7i\2\2&\'\7t\2\2\'(\7c\2\2()\7o\2\2)*\7o\2\2*+\7c\2\2+,\7t"+
		"\2\2,\4\3\2\2\2-.\7=\2\2.\6\3\2\2\2/\60\7<\2\2\60\b\3\2\2\2\61\62\7~\2"+
		"\2\62\n\3\2\2\2\63\64\7,\2\2\64\f\3\2\2\2\65\66\7*\2\2\66\16\3\2\2\2\67"+
		"8\7+\2\28\20\3\2\2\29:\7B\2\2:;\7j\2\2;<\7g\2\2<=\7c\2\2=>\7f\2\2>?\7"+
		"g\2\2?@\7t\2\2@\22\3\2\2\2AB\7B\2\2BC\7o\2\2CD\7g\2\2DE\7o\2\2EF\7d\2"+
		"\2FG\7g\2\2GH\7t\2\2HI\7u\2\2I\24\3\2\2\2JK\7]\2\2K\26\3\2\2\2LM\7.\2"+
		"\2M\30\3\2\2\2NO\7_\2\2O\32\3\2\2\2PR\t\2\2\2QP\3\2\2\2RS\3\2\2\2SQ\3"+
		"\2\2\2ST\3\2\2\2TU\3\2\2\2UV\b\16\2\2V\34\3\2\2\2W[\7)\2\2XZ\13\2\2\2"+
		"YX\3\2\2\2Z]\3\2\2\2[\\\3\2\2\2[Y\3\2\2\2\\^\3\2\2\2][\3\2\2\2^_\7)\2"+
		"\2_\36\3\2\2\2`d\t\3\2\2ac\t\4\2\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2"+
		"\2\2e \3\2\2\2fd\3\2\2\2gl\5\37\20\2hi\7\60\2\2ik\5\37\20\2jh\3\2\2\2"+
		"kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mv\3\2\2\2nl\3\2\2\2op\7>\2\2pq\5!\21\2"+
		"qr\7.\2\2rs\5!\21\2st\3\2\2\2tu\7@\2\2uw\3\2\2\2vo\3\2\2\2vw\3\2\2\2w"+
		"z\3\2\2\2xy\7]\2\2y{\7_\2\2zx\3\2\2\2z{\3\2\2\2{~\3\2\2\2|~\5\37\20\2"+
		"}g\3\2\2\2}|\3\2\2\2~\"\3\2\2\2\177\u008a\7}\2\2\u0080\u0082\n\5\2\2\u0081"+
		"\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2"+
		"\2\2\u0084\u0086\3\2\2\2\u0085\u0087\5#\22\2\u0086\u0085\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0081\3\2\2\2\u0089\u008c\3\2"+
		"\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008d\u008e\7\177\2\2\u008e$\3\2\2\2\r\2S[dlvz}\u0083\u0086"+
		"\u008a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}