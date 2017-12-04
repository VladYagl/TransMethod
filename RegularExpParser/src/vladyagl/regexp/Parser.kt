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

            Token.Type.LETTER -> {
                Node("S", Node(lex.token.symbol.toString()), {lex.nextToken(); parseSPrime()}())
            }

            Token.Type.LPAREN -> {
                lex.nextToken()
                val inside = parseS()
                if (lex.token.type != Token.Type.RPAREN)
                    throw ParseException("Syntax error", lex.position)
                lex.nextToken()
                Node("S", Node("("), inside, Node(")"), parseSPrime())
            }

            Token.Type.RPAREN,
            Token.Type.ALTER,
            Token.Type.KLEENE,
            Token.Type.END -> throw ParseException("Syntax error", lex.position)
        }
    }

    private fun parseSPrime(): Node {
        return when (lex.token.type) {

            Token.Type.LETTER,
            Token.Type.LPAREN -> {
                val first = parseS()
                val second = parseSPrime()
                Node("S'", first, second)
            }

            Token.Type.ALTER -> {
                lex.nextToken()
                val first = parseS()
                val second = parseSPrime()
                Node("S'", Node("|"), first, second)
            }

            Token.Type.KLEENE -> {
                lex.nextToken()
                Node("S'", Node("*"), parseSPrime())
            }

            Token.Type.END,
            Token.Type.RPAREN -> Node("S'")
        }
    }

    fun parse(): Node {
        lex.nextToken()
        return parseS()
    }
}