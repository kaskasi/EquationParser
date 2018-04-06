package de.fluchtwege.equationparser

import java.util.*

/**
 * This class converts an infix to a postfix expression using the
 * shunting yard algorithm https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 */
internal class InfixToPostfixConverter(private val tokens: List<Token>) {

    private val operatorStack = Stack<Token>()
    private val postfixResult = mutableListOf<Token>()

    fun convert(): List<Token> {
        for (token in tokens) {
            when (token) {
                is Token.Operand -> onOperand(token)
                is Token.Operator -> onOperator(token)
                is Token.OpenParenthesis -> onOpenParenthesis(token)
                is Token.CloseParenthesis -> onCloseParenthesis()
            }
        }
        while(operatorStack.isNotEmpty()) {
            postfixResult.add(operatorStack.pop())
        }
        return postfixResult
    }

    private fun onOperand(operand: Token.Operand) {
        postfixResult.add(operand)
    }

    private fun onOperator(operator: Token.Operator) {
        while (shouldPopTopOfStack(operator)) {
            postfixResult.add(operatorStack.pop())
        }
        operatorStack.push(operator)
    }

    private fun shouldPopTopOfStack(operator: Token.Operator) = when {
        operatorStack.isEmpty() -> false
        isOpenParenthesisOnStack() -> false
        isOperatorWithHigherPrecedenceOnStack(operator) -> true
        else -> false
    }

    private fun isOpenParenthesisOnStack() = operatorStack.peek() is Token.OpenParenthesis

    private fun isOperatorWithHigherPrecedenceOnStack(operator: Token.Operator) = (operatorStack.peek() as Token.Operator).getPrecedence() >= operator.getPrecedence()

    private fun onOpenParenthesis(openParenthesis: Token.OpenParenthesis) {
        operatorStack.push(openParenthesis)
    }

    private fun onCloseParenthesis() {
        while (operatorStack.peek() !is Token.OpenParenthesis) {
            postfixResult.add(operatorStack.pop())
        }
        operatorStack.pop()
    }
}