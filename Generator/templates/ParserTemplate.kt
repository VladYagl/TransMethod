import java.awt.SystemColor.text
import java.io.Reader

//$header

class ParserTemplate(reader: Reader) {

//$members

    open class Node(val name: String) {
        val children = ArrayList<Node>()
        var text: String = ""

        fun addChild(child: Node) {
            children.add(child)
            text += child.text
        }

        fun toStringTree(level: Int): String {
            return "|  ".repeat(level) + "Node[" + name + "]" + if (children.isNotEmpty()) {
                "\n" + children.map { it.toStringTree(level + 1) }.joinToString(separator = "\n")
            } else {
                ""
            }
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