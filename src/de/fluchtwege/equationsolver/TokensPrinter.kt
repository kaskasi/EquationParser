package de.fluchtwege.equationsolver

/**
 * This class may be used to print a collection of tokens for debugging
 */
internal class TokensPrinter {

    fun printTokens(name: String, tokens: Collection<Token>) {
        System.out.println(name)
        tokens.iterator().forEach { printToken(it) }
        System.out.println()
        System.out.println()
    }

    private fun printToken(token: Token) {
        val printValue = when (token) {
            is Token.Operand -> token.value.toString()
            is Token.Operator -> getStringForOperator(token)
            is Token.OpenParenthesis -> "("
            is Token.CloseParenthesis -> ")"
        }
        System.out.print(printValue)
    }

    private fun getStringForOperator(token: Token.Operator) = when (token.operatorType) {
        is OperatorType.Addition -> "+"
        is OperatorType.Subtraction -> "-"
        is OperatorType.Multiplication -> "*"
        is OperatorType.Division -> "/"
        is OperatorType.Power -> "^"
    }
}