package de.fluchtwege.equationparser

import java.util.*

/**
 * This class evaluates a list of tokens
 * consisting of operators and operands in postfix notation
 * to an integer value
 */
internal class PostfixEvaluator(private val tokens: List<Token>) {


    val evaluationStack = Stack<Token.Operand>()

    fun evaluate(): Int {
        tokens.forEach {
            when(it) {
                is Token.Operand -> onOperand(it)
                is Token.Operator -> onOperator(it)
            }
        }
        val evaluationResult = evaluationStack.pop().value
        return evaluationResult.toInt()
    }

    private fun onOperand(operand: Token.Operand) {
        evaluationStack.push(operand)
    }

    private fun onOperator(operator: Token.Operator) {
        require(evaluationStack.size > 1, {"Input Error - not postfix notation"})
        val secondArgument = evaluationStack.pop()
        val firstArgument = evaluationStack.pop()
        val operationResult = operator.operatorType.evaluate(firstArgument, secondArgument)
        evaluationStack.push(Token.Operand(operationResult))
    }
}