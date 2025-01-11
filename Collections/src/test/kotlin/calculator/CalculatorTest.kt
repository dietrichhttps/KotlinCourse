package calculator

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class CalculatorTest {

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `When Add 5 To 10 Then Result 15`(calculator: Calculator) {
        val result = calculator.sum(10, 5)
        val expected = 15
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `When Add 50 To 100 Then Result 15`(calculator: Calculator) {
        val result = calculator.sum(100, 50)
        val expected = 150
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `When Subtract 10 From 5 Then Result 5`(calculator: Calculator) {
        val result = calculator.subtraction(10, 5)
        val expected = 5
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `When Subtract 100 From 50 Then Result 50`(calculator: Calculator) {
        val result = calculator.subtraction(100, 50)
        val expected = 50
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `When Multiply 5 By 10 Then Result 50`(calculator: Calculator) {
        val result = calculator.multiplication(10, 5)
        val expected = 50
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `When Multiply 5 By 0 Then Result 0`(calculator: Calculator) {
        val result = calculator.multiplication(5, 0)
        val expected = 0
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `When 10 Divide By 5 Then Result 2`(calculator: Calculator) {
        val result = calculator.division(10, 5)
        val expected = 2.0
        assertEquals(expected, result, 0.001)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `When 0 Divide By 1000 Then Result 0`(calculator: Calculator) {
        val result = calculator.division(0, 1000)
        val expected = 0.0
        assertEquals(expected, result, 0.001)
    }

    companion object {

        @JvmStatic
        fun calculatorsSource() = listOf(SimpleCalculator(), LoggingCalculator())
    }
}