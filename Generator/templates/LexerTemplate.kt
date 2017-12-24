import java.io.Reader

class ParseException(msg: String, position: Int? = null) : Exception(msg + if (position != null) {" at $position"} else {""})

open class Token(val type: Type, var text: String) {
    enum class Type {
//$types
        END
    }
}

class LexerTemplate (var reader: Reader) {

    private var lastToken: Token? = null
    val token: Token
        get() = lastToken ?: throw ParseException("No last token")

    private var pos = 0
    val position: Int
        get() = pos - 1

    fun nextToken(): Token {
        val sym = reader.read()
        pos++
        lastToken = when (sym) {
            -1 -> Token(Token.Type.END, "")
//            '$'.toInt() -> Token(Token.Type.END, "")
//$choice
            else -> throw ParseException("Illegal character: '$sym'", position)
        }
        print(sym.toChar())
        return token
    }
}
