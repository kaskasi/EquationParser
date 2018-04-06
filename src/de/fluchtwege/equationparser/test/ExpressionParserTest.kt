package de.fluchtwege.equationparser.test

import org.junit.Test
import de.fluchtwege.equationparser.ExpressionParser
import de.fluchtwege.equationparser.OperatorType
import de.fluchtwege.equationparser.Token
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ExpressionParserTest {

    @Test
    fun testParseExpression() {
        val input = "2 + 3 / 6"
        val parser = ExpressionParser(input)
        val actual = parser.tokenize()

        assertEquals(5, actual.size)
        assertEquals(2.0, (actual[0] as Token.Operand).value)
        assertTrue((actual[1] as Token.Operator).operatorType is OperatorType.Addition)
        assertEquals(3.0, (actual[2] as Token.Operand).value)
        assertTrue((actual[3] as Token.Operator).operatorType is OperatorType.Division)
        assertEquals(6.0, (actual[4] as Token.Operand).value)
    }
}