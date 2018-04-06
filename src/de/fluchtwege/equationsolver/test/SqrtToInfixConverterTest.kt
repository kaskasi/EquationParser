package de.fluchtwege.equationsolver.test

import org.junit.Test
import de.fluchtwege.equationsolver.SqrtToInfixConverter
import kotlin.test.assertEquals

class SqrtToInfixConverterTest {

    @Test
    fun testParseExpression_noSqrt_noChange() {
        val input = "2 + 3 / 6"
        val converter = SqrtToInfixConverter()
        val actual = converter.convert(input)
        assertEquals(input, actual)
    }

    @Test
    fun testParseExpression_containsSqrt_replaced() {
        val input = "sqrt(4)"
        val converter = SqrtToInfixConverter()
        val actual = converter.convert(input)
        assertEquals("4 ^ ( 1 / 2 )", actual)
    }

    @Test
    fun testParseExpression_expresionInSqrt_replacedWithParenthesis() {
        val input = "sqrt(3 * 2 / 6)"
        val converter = SqrtToInfixConverter()
        val actual = converter.convert(input)
        assertEquals("( 3 * 2 / 6 ) ^ ( 1 / 2 )", actual)
    }

    @Test
    fun testParseExpression_nestedSqurt_replaced() {
        val input = "sqrt(4 * sqrt(16))"
        val converter = SqrtToInfixConverter()
        val actual = converter.convert(input)
        assertEquals("( 4 * 16 ^ ( 1 / 2 ) ) ^ ( 1 / 2 )", actual)
    }
}