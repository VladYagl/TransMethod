grammar Grammar;

main : 'grammar' grammarName = NAME ';' (grammarRule ';')*;

grammarRule : name = NAME ':' (term ('|' term)* | expression);

expression : nonterm ('|' nonterm)*;

term: (CONST)+;

nonterm: (unar)+ | ;

unar : unar'*' | ruleName = NAME | '(' expression ')' | token = CONST;


WHITESPACE : [ \t\r\n]+ -> skip;
CONST : '\'' .*? '\'';
NAME : [_a-zA-Z][_a-zA-Z0-9]*;

TEST : 'asd';
