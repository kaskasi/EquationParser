package de.fluchtwege.equationparser.test

import org.junit.Test
import de.fluchtwege.equationparser.EquationParser
import kotlin.test.assertEquals

class EquationParserTest {

    @Test
    fun testParse_precedence() {
        val expression = "4 * 5 + 2"
        val actual = EquationParser().parse(expression)
        assertEquals(22, actual)
    }

    @Test
    fun testParse_sqrt() {
        val expression = "2 * sqrt(4)"
        val actual = EquationParser().parse(expression)
        assertEquals(4, actual)
    }

    @Test
    fun testParse_parenthesis() {
        val expression = "304 / 16 + 11 * (16 + 19)"
        val actual = EquationParser().parse(expression)
        assertEquals(404, actual)
    }

    @Test
    fun testParse_precedenceOrder() {
        val expression = "32 * 4 + 1 + 6 * 34 * 8 - 12 * 52"
        val actual = EquationParser().parse(expression)
        assertEquals(1137, actual)
    }

    @Test
    fun testParse_nestedSqrt() {
        val expression = "sqrt(32 * 4 / (2 + 6) * 113 - 8 * 12 + 52)"
        val actual = EquationParser().parse(expression)
        assertEquals(42, actual)
    }
}