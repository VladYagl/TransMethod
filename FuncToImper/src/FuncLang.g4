grammar FuncLang;

@header {
import java.util.Collections;
}

@members {
    private String indent(int n) {
        return String.join("", Collections.nCopies(n, "   "));
    }

    private String addReturn(boolean ret) {
        if (ret) {
            return "return ";
        } else {
            return "";
        }
    }
}

main
	: (expression = expr[0, false] {if ($expression.res != null) {System.out.println($expression.res);}})*;

expr[int level, boolean ret] returns [String res]
    : 'let' var = NAME ':' type = NAME '=' rep = expr[0, false] 'in' e = expr[level, ret] {
        $res = indent($level) + $type.text + " " + $var.text + " = " + $rep.res + ";\n" + $e.res;
    }
    | 'if' condition = expr[0, false] 'then' then = expr[level + 1, ret] 'else' elseExpr = expr[level + 1, ret] {
        $res = indent($level)
            + "if (" + $condition.res + ") {\n" + $then.res + ";\n"
            + indent($level) +"} else {\n" + $elseExpr.res + ";\n"
            + indent($level) + "}";
    }
    | 'func' name = NAME '(' args = typelist ')' ':' type = NAME '=' body = expr[level + 1, true] {
        $res = indent($level) + $type.text + " " + $name.text + "(" + $args.res + ") {\n" + $body.res + ";\n}";
    }
    | l = logic {$res = indent($level) + addReturn($ret) + $l.res;};

logic returns [String res]
    : first = cond {$res = $first.res;}
        ( '||' second = cond {$res += " || " + $second.res;}
        | '&&' second = cond {$res += " && " + $second.res;}
        )* ;

cond returns [String res]
    : first = arith {$res = $first.res;}
        ( '=' second = arith {$res += " == " + $second.res;}
        | '<' second = arith {$res += " < " + $second.res;}
        | '>' second = arith {$res += " > " + $second.res;}
        | '<=' second = arith {$res += " <= " + $second.res;}
        | '>=' second = arith {$res += " >= " + $second.res;}
        | '!=' second = arith {$res += " != " + $second.res;}
        )* ;

arith returns [String res]
    : first = mathMul {$res = $first.res;}
        ( '-' second = mathMul {$res += " - " + $second.res;}
        | '+' second = mathMul {$res += " + " + $second.res;}
        )* ;

mathMul returns [String res]
    : first = term {$res = $first.res;}
        ( '*' second = mathMul {$res += " * " + $second.res;}
        | '/' second = mathMul {$res += " / " + $second.res;}
        | '%' second = mathMul {$res += " % " + $second.res;}
        )* ;

term returns [String res]
    : CONST {$res = $CONST.text;}
    | f = NAME '(' args = list ')' {$res = $f.text + "(" + $args.res + ")";}
    | NAME {$res = $NAME.text;}
    | '(' e = expr[0, false] ')' {$res = "(" + $e.res + ")";}
    | '!' t = term {$res = "!(" + $t.res + ")";};

list returns [String res]
    : e = expr[0, false] {$res = $e.res;}
    | l = list ',' e = expr[0, false] {$res = $l.res + ", " + $e.res;}
    | {$res = "";}
    ;

typelist returns [String res]
    : e = expr[0, false] ':' type = NAME {$res = $type.text + " " + $e.res;}
    | l = typelist ',' e = expr[0, false] ':' type = NAME {$res = $l.res + ", " + $type.text + " " + $e.res;}
    | {$res = "";}
    ;

WHITESPACE : [ \t\r\n]+ -> skip;
CONST : [-]?[0-9]+ | '"'[_a-zA-Z0-9]*'"';
NAME : [_a-zA-Z][_a-zA-Z0-9]*;
