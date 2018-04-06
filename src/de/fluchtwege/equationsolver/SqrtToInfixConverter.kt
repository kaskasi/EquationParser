package de.fluchtwege.equationsolver

import java.util.*

/**
 * This class prepares the mathematical expression for evaluation by replacing square root operators
 * by to the power of half operations. This class also ensures that parenthesis content is separated
 * by spaces from parenthesis
 */
internal class SqrtToInfixConverter {

    private val SQRT = "sqrt"
    private val INFIX_SQRT = "^ (1 / 2)"

    fun convert(expression: String): String {
        var convertedExpression = expression
        var expressionStartIndex = convertedExpression.indexOf(SQRT)
        while (expressionStartIndex > -1) {
            convertedExpression = replaceSqrtWithInfix(expressionStartIndex, convertedExpression)
            expressionStartIndex = convertedExpression.indexOf(SQRT)
        }
        return addSpacesToParenthesis(convertedExpression)
    }

    private fun addSpacesToParenthesis(expression: String): String {
        return expression
                .replace(OPEN_PARENTHESIS, "$OPEN_PARENTHESIS ")
                .replace(CLOSE_PARENTHESIS, " $CLOSE_PARENTHESIS")
    }

    private fun replaceSqrtWithInfix(expressionStartIndex: Int, expression: String): String {
        val expressionEndIndex = findEndOfSqrtExpression(expressionStartIndex, expression)
        val sqrtExpression = expression.substring(expressionStartIndex + SQRT.length + 1, expressionEndIndex)
        val originalExpression = expression.substring(expressionStartIndex, expressionEndIndex + 1)
        val replacementPattern = if (sqrtExpression.contains(" ")) "($sqrtExpression) $INFIX_SQRT" else "$sqrtExpression $INFIX_SQRT"
        return expression.replace(originalExpression, replacementPattern)
    }

    private fun findEndOfSqrtExpression(expressionStartIndex: Int, expression: String): Int {
        var currentIndex = expressionStartIndex
        val parenthesisStack = Stack<Char>()
        currentIndex += SQRT.length
        parenthesisStack.push(expression[currentIndex])
        while (parenthesisStack.isNotEmpty()) {
            currentIndex++
            val currentChar = expression[currentIndex]
            if (currentChar == '(') {
                parenthesisStack.push(currentChar)
            } else if (currentChar == ')') {
                parenthesisStack.pop()
            }
        }
        return currentIndex
    }
}