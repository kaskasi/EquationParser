package de.fluchtwege.equationsolver.test

import de.fluchtwege.equationsolver.OperatorType
import de.fluchtwege.equationsolver.PostfixEvaluator
import de.fluchtwege.equationsolver.Token
import org.junit.Test
import kotlin.test.assertEquals

class PostfixEvaluatorTest {

    @Test
    fun testEvaluate_precedence() {
        val postfixTokens = listOf(
                Token.Operand(1.0),
                Token.Operand(2.0),
                Token.Operand(3.0),
                Token.Operator(OperatorType.Multiplication()),
                Token.Operator(OperatorType.Addition()),
                Token.Operand(4.0),
                Token.Operator(OperatorType.Subtraction()))
        val evaluted = PostfixEvaluator(postfixTokens).evaluate()
        assertEquals(3, evaluted)
    }

    @Test
    fun testEvaluate_precedenceOrder() {
        val postfixTokens = listOf(
                Token.Operand(4.0),
                Token.Operand(5.0),
                Token.Operator(OperatorType.Multiplication()),
                Token.Operand(2.0),
                Token.Operator(OperatorType.Addition()))
        val evaluted = PostfixEvaluator(postfixTokens).evaluate()
        assertEquals(22, evaluted)
    }

    @Test
    fun testConvert_parenthesis() {
        val postfixTokens = listOf(
                Token.Operand(4.0),
                Token.Operand(18.0),
                Token.Operand(9.0),
                Token.Operand(3.0),
                Token.Operator(OperatorType.Subtraction()),
                Token.Operator(OperatorType.Division()),
                Token.Operator(OperatorType.Addition()))
        val evaluated = PostfixEvaluator(postfixTokens).evaluate()
        assertEquals(7, evaluated)
    }

    @Test
    fun testConvert_sqrt() {
        val postfixTokens = listOf(
                Token.Operand(2.0),
                Token.Operand(4.0),
                Token.Operand(1.0),
                Token.Operand(2.0),
                Token.Operator(OperatorType.Division()),
                Token.Operator(OperatorType.Power()),
                Token.Operator(OperatorType.Multiplication()))
        val evaluated = PostfixEvaluator(postfixTokens).evaluate()
        assertEquals(4, evaluated)
    }
}