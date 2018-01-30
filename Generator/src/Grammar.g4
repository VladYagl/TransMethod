grammar Grammar;

@header {
    import java.util.HashMap;
}

main : 'grammar' grammarName = NAME ';' header? members? (grammarRule ';')*;

grammarRule : name = NAME arg = argsDef? ('attrs' attr = argsDef)? ':' (term ('|' term)* | expression);

term: (CONST)+;

expression : nonterm ('|' nonterm)*;

nonterm: unar* (CODE)?;

unar : unar'*' | ruleName = NAME args? | '(' expression ')' | token = CONST;

header : '@header' CODE;
members : '@members' CODE;

argsDef returns [HashMap<String, String> attrs = new HashMap<>()] : '(' typedArg {$attrs.put($typedArg.name, $typedArg.typeT);} (',' typedArg {$attrs.put($typedArg.name, $typedArg.typeT);})* ')';
typedArg returns [String name, String typeT] : NAME ':' type {$name = $NAME.text; $typeT = $type.text;};
type : NAME ('.' NAME)* ('<'type (',' type)'>')? ('[]')?;

args : ARGS;

CONST : '\'' (~'\'' | '\\\'')* '\'';


WHITESPACE : [ \t\r\n]+ -> skip;

NAME : [_a-zA-Z][_a-zA-Z0-9]*;
CODE : '{' (~[{}]+ CODE?)* '}';
ARGS : '[' ~[[\]]+ ']';
