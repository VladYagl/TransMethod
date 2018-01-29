grammar Grammar;

main : 'grammar' grammarName = NAME ';' header? members? (grammarRule ';')*;

grammarRule : name = NAME argsDef? ':' (term ('|' term)* | expression);

term: (CONST)+;

expression : nonterm ('|' nonterm)*;

nonterm: unar* CODE?;

unar : unar'*' | ruleName = NAME args? | '(' expression ')' | token = CONST;

header : '@header' CODE;
members : '@members' CODE;

argsDef : '[' typedArg (',' typedArg)* ']';
typedArg : NAME ':' TYPE;
args : '[' CODE (',' CODE)* ']';

WHITESPACE : [ \t\r\n]+ -> skip;
CONST : '\'' .*? '\'';
NAME : [_a-zA-Z][_a-zA-Z0-9]*;

TYPE : NAME('.'NAME)*('<'TYPE (',' TYPE)'>')?'[]'? | NAME;
CODE : '{' (~[{}]+ CODE?)* '}';

