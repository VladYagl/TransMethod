package vladyagl.regexp

open class Token(val type: Type, var symbol: Char) {
    enum class Type {
        LETTER,
        LPAREN,
        RPAREN,
        ALTER,
        KLEENE,
        END
    }
}

class Lparen: Token(Type.LPAREN, '(')
class Rparen: Token(Type.RPAREN, ')')
class Alter: Token(Type.ALTER, '|')
class Kleene: Token(Type.KLEENE, '*')
class End: Token(Type.END, '$')
class Letter(letter: Char): Token(Type.LETTER, letter)
