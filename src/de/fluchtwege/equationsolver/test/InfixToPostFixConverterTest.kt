package de.fluchtwege.equationsolver.test

import de.fluchtwege.equationsolver.InfixToPostfixConverter
import de.fluchtwege.equationsolver.OperatorType
import de.fluchtwege.equationsolver.Token
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InfixToPostFixConverterTest {

    @Test
    fun testConvert_differentPrecedences() {
        val tokens = listOf(
                Token.Operand(2.0),
                Token.Operator(OperatorType.Multiplication()),
                Token.Operand(3.0),
                Token.Operator(OperatorType.Subtraction()),
                Token.Operand(4.0),
                Token.Operator(OperatorType.Division()),
                Token.Operand(5.0))
        val postfixTokens = InfixToPostfixConverter(tokens).convert()
        assertEquals(2.0, (postfixTokens[0] as Token.Operand).value)
        assertEquals(3.0, (postfixTokens[1] as Token.Operand).value)
        assertTrue((postfixTokens[2] as Token.Operator).operatorType is OperatorType.Multiplication)
        assertEquals(4.0, (postfixTokens[3] as Token.Operand).value)
        assertEquals(5.0, (postfixTokens[4] as Token.Operand).value)
        assertTrue((postfixTokens[5] as Token.Operator).operatorType is OperatorType.Division)
        assertTrue((postfixTokens[6] as Token.Operator).operatorType is OperatorType.Subtraction)
    }

    @Test
    fun testConvert_differentPrecedencesWithParenthesis() {
        val tokens = listOf(
                Token.Operand(4.0),
                Token.Operator(OperatorType.Addition()),
                Token.Operand(18.0),
                Token.Operator(OperatorType.Division()),
                Token.OpenParenthesis(),
                Token.Operand(9.0),
                Token.Operator(OperatorType.Subtraction()),
                Token.Operand(3.0),
                Token.CloseParenthesis())
        val postfixTokens = InfixToPostfixConverter(tokens).convert()
        assertEquals(4.0, (postfixTokens[0] as Token.Operand).value)
        assertEquals(18.0, (postfixTokens[1] as Token.Operand).value)
        assertEquals(9.0, (postfixTokens[2] as Token.Operand).value)
        assertEquals(3.0, (postfixTokens[3] as Token.Operand).value)
        assertTrue((postfixTokens[4] as Token.Operator).operatorType is OperatorType.Subtraction)
        assertTrue((postfixTokens[5] as Token.Operator).operatorType is OperatorType.Division)
        assertTrue((postfixTokens[6] as Token.Operator).operatorType is OperatorType.Addition)
    }

}