package de.fluchtwege.equationparser

/**
 * This class calculates the value of mathematical expression.
 * The expression is first parsed and converted to a list of operators and operands in infix notation.
 * The infix tokens are then converted to postfix notation.
 * Finally the postfix tokens are evaluated
 */
class EquationParser {

    fun parse(expression: String): Int {
        val tokens = ExpressionParser(expression).parse()
        val postfixTokens = InfixToPostfixConverter(tokens).convert()
        return PostfixEvaluator(postfixTokens).evaluate()
    }
}