package de.fluchtwege.equationparser

internal sealed class Token {
    class Operator(val operatorType: OperatorType) : Token() {
        fun getPrecedence() = operatorType.precedence
    }

    class Operand(val value: Double) : Token()
    class OpenParenthesis : Token()
    class CloseParenthesis : Token()
}
