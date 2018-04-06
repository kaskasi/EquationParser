package de.fluchtwege.equationparser

internal sealed class OperatorType(open val precedence: Int) {


    class Addition(override val precedence: Int = 0) : OperatorType(precedence) {
        override fun evaluate(lhs: Token.Operand, rhs: Token.Operand) = lhs.value + rhs.value
    }

    class Subtraction(override val precedence: Int = 0) : OperatorType(precedence) {
        override fun evaluate(lhs: Token.Operand, rhs: Token.Operand) = lhs.value - rhs.value
    }

    class Multiplication(override val precedence: Int = 1) : OperatorType(precedence) {
        override fun evaluate(lhs: Token.Operand, rhs: Token.Operand) = lhs.value * rhs.value
    }

    class Division(override val precedence: Int = 3) : OperatorType(precedence) {
        override fun evaluate(lhs: Token.Operand, rhs: Token.Operand) = lhs.value / rhs.value
    }

    class Power(override val precedence: Int = 2) : OperatorType(precedence) {
        override fun evaluate(lhs: Token.Operand, rhs: Token.Operand) = Math.pow(lhs.value, rhs.value)
    }

    abstract fun evaluate(lhs: Token.Operand, rhs: Token.Operand): Double
}
