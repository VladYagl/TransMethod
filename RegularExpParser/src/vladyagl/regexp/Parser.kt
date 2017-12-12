package vladyagl.regexp

import java.io.Reader

class Parser(reader: Reader) {

    class Node(val name: String, vararg args: Node) {
        val children = args
        fun toStringTree(level: Int): String {
            return "|  ".repeat(level) + "Node[" + name + "]" + if (children.isNotEmpty()) {"\n" + children.map{it.toStringTree(level + 1)}.joinToString(separator = "\n")} else {""}
        }

        override fun toString(): String {
            return toStringTree(0)
        }
    }

    val lex = LexicalAnalyser(reader)

    private fun parseS(): Node {
        return when (lex.token.type) {
            Token.Type.LPAREN,
            Token.Type.LETTER -> {
                Node("S", parseT(), parseSPrime())
            }

            Token.Type.RPAREN,
            Token.Type.ALTER,
            Token.Type.KLEENE,
            Token.Type.END -> throw ParseException("Syntax error", lex.position)
        }
    }

    private fun parseSPrime(): Node {
        return when (lex.token.type) {
            Token.Type.ALTER -> {
                lex.nextToken()
                Node("S'", Node("|"), parseT(), parseSPrime())
            }

            Token.Type.LETTER,
            Token.Type.LPAREN,
            Token.Type.KLEENE,
            Token.Type.END,
            Token.Type.RPAREN -> Node("S'")
        }
    }

    private fun parseT(): Node {
        return when (lex.token.type) {
            Token.Type.LPAREN,
            Token.Type.LETTER -> {
                Node("T", parseF(), parseTPrime())
            }

            Token.Type.RPAREN,
            Token.Type.ALTER,
            Token.Type.KLEENE,
            Token.Type.END -> throw ParseException("Syntax error", lex.position)
        }
    }

    private fun parseTPrime(): Node {
        return when (lex.token.type) {
            Token.Type.LETTER,
            Token.Type.LPAREN -> {
                Node("T'", parseF(), parseTPrime())
            }

            Token.Type.KLEENE,
            Token.Type.ALTER,
            Token.Type.END,
            Token.Type.RPAREN -> Node("S'")
        }
    }

    private fun parseF(): Node {
        val token = lex.token
        return when (token.type) {
            Token.Type.LPAREN -> {
                lex.nextToken()
                Node("F", Node("("), parseS(), {lex.nextToken(); parseFPrime()}(), Node(")"))
            }
            Token.Type.LETTER -> {
                Node("F", Node("" + token.symbol), {lex.nextToken(); parseFPrime()}())
            }

            Token.Type.RPAREN,
            Token.Type.ALTER,
            Token.Type.KLEENE,
            Token.Type.END -> throw ParseException("Syntax error", lex.position)
        }
    }

    private fun parseFPrime(): Node {
        return when (lex.token.type) {
            Token.Type.KLEENE -> {
                lex.nextToken()
                Node("F'", Node("*"), parseFPrime())
            }

            Token.Type.LETTER,
            Token.Type.LPAREN,
            Token.Type.ALTER,
            Token.Type.END,
            Token.Type.RPAREN -> Node("F'")
        }
    }

    fun parse(): Node {
        lex.nextToken()
        return parseS()
    }
}