package generator

import com.sun.xml.internal.org.jvnet.fastinfoset.sax.FastInfosetReader
import org.antlr.v4.runtime.tree.ParseTree
import java.awt.SystemColor.text

open class Expression(val canBeEmpty: Boolean = false, val emptyChoice: Production? = null) {
    open val first: MutableMap<Token, Production> = HashMap()
    open val last: MutableSet<Rule> = HashSet()
    open val follow: MutableSet<Token> = HashSet()
    open val tokens: MutableSet<Token> = HashSet()

    val choices = HashSet<Production>()

    var args : String = ""
    val attrs = hashMapOf("text" to "String")

    fun add(other: Production) {
        first.putAll(other.first)
        tokens.addAll(other.tokens)
        choices.add(other)
    }

    override fun toString(): String = choices.joinToString(separator = " | ", postfix = ")", prefix = "(") { it.toString() }
}

open class Production(canBeEmpty: Boolean = false, val code: String = "") : Expression(canBeEmpty) {
    val list = ArrayList<Expression>()

    override fun toString(): String = list.joinToString(separator = " ", postfix = "]", prefix = "[") { it.toString() }
}

private var tokenCount = 0
private fun genName(): String {
    return "Token_" + tokenCount++
}

open class Token(val text: String, val name: String = genName()) : Production() {

    override fun equals(other: Any?): Boolean = if (other is Token) text == other.text else false

    override fun hashCode(): Int = text.hashCode()

    override val first = mutableMapOf(this to this as Production)
    override val tokens = mutableSetOf(this)

    override fun toString(): String = text
}

class ComplexToken(name: String) : Token("Token_$name", name) {
    override val first = HashMap<Token, Production>()
    override val tokens = HashSet<Token>()

    override fun toString(): String = name
}

class Rule(val ruleName: String, val callArgs: String) : Token("ruleName = '$ruleName'", "") {
    override val last = mutableSetOf(this)

    override fun toString(): String = ruleName
}


