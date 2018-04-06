package de.fluchtwege.equationsolver

const val ADDITION = "+"
const val SUBTRACTION = "-"
const val MULTIPLICATION = "*"
const val DIVISION = "/"
const val POWER = "^"
const val OPEN_PARENTHESIS = "("
const val CLOSE_PARENTHESIS = ")"

/**
 * This class parses a mathematical expression from a string
 * and returns a list of tokens consisting of operators and operands in postfix notation
 *
 * requires: Operators and Values in expression are separated by spaces
 */
internal class ExpressionTokenizer(private val expression: String) {

    fun tokenize() : List<Token> {
        val convertedExpression = SqrtToInfixConverter().convert(expression)
        val splitExpression = convertedExpression.split(" ")
        return splitExpression.map { getToken(it) }
    }

    private fun getToken(token: String): Token {
        return when (token) {
            ADDITION -> Token.Operator(OperatorType.Addition())
            SUBTRACTION -> Token.Operator(OperatorType.Subtraction())
            MULTIPLICATION -> Token.Operator(OperatorType.Multiplication())
            DIVISION -> Token.Operator(OperatorType.Division())
            POWER -> Token.Operator(OperatorType.Power())
            OPEN_PARENTHESIS -> Token.OpenParenthesis()
            CLOSE_PARENTHESIS -> Token.CloseParenthesis()
            else -> Token.Operand(token.toDouble())
        }
    }
}