import java.io.Reader

class ParserTemplate(reader: Reader) {

    class Node(val name: String, vararg args: Node) {
        val children = args
        fun toStringTree(level: Int): String {
            return "|  ".repeat(level) + "Node[" + name + "]" + if (children.isNotEmpty()) {"\n" + children.map{it.toStringTree(level + 1)}.joinToString(separator = "\n")} else {""}
        }

        override fun toString(): String {
            return toStringTree(0)
        }
    }

    val lex = LexerTemplate(reader)

//$rules

    fun parse(): Node {
        lex.nextToken()
        return Node("RootRule")
    }
}