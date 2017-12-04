package vladyagl.regexp

import java.io.Reader

class LexicalAnalyser(var reader: Reader) {

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
            -1 -> End()
            '('.toInt() -> Lparen()
            ')'.toInt() -> Rparen()
            '|'.toInt() -> Alter()
            '*'.toInt() -> Kleene()
            ' '.toInt(), '\n'.toInt(), '\r'.toInt(), '\t'.toInt() -> nextToken()
            in 'a'.toInt()..'z'.toInt() -> Letter(sym.toChar())
            else -> throw ParseException("Illegal character: '$sym'", position)
        }
        print(sym.toChar())
        return token
    }
}

