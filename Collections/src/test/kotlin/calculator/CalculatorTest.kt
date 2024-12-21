package calculator

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalculatorTest {

    private val calculator = Calculator()

    @Test
    fun `When Add 5 To 10 Then Result 15`() {
        val result = calculator.sum(10, 5)
        val expected = 15
        assertEquals(expected, result)
    }

    @Test
    fun `When Add 50 To 100 Then Result 15`() {
        val result = calculator.sum(100, 50)
        val expected = 150
        assertEquals(expected, result)
    }

    @Test
    fun `When Subtract 10 From 5 Then Result 5`() {
        val result = calculator.subtraction(10, 5)
        val expected = 5
        assertEquals(expected, result)
    }

    @Test
    fun `When Subtract 100 From 50 Then Result 50`() {
        val result = calculator.subtraction(100, 50)
        val expected = 50
        assertEquals(expected, result)
    }

    @Test
    fun `When Multiply 5 By 10 Then Result 50`() {
        val result = calculator.multiplication(10, 5)
        val expected = 50
        assertEquals(expected, result)
    }

    @Test
    fun `When Multiply 5 By 0 Then Result 0`() {
        val result = calculator.multiplication(5, 0)
        val expected = 0
        assertEquals(expected, result)
    }

    @Test
    fun `When 10 Divide By 5 Then Result 2`() {
        val result = calculator.division(10, 5)
        val expected = 2.0
        assertEquals(expected, result, 0.001)
    }

    @Test
    fun `When 0 Divide By 1000 Then Result 0`() {
        val result = calculator.division(0, 1000)
        val expected = 0.0
        assertEquals(expected, result, 0.001)
    }
}