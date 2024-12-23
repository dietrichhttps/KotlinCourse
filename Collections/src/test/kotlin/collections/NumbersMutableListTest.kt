package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class NumbersMutableListTest {

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 1 element then size is 1`(list: NumbersMutableList) {
        list.add(0)
        assertEquals(1, list.size  )
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 elements then size is 10`(list: NumbersMutableList) {
        repeat(10) {
            list.add(it)
        }
        assertEquals(10, list.size  )
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 5th element then result is correct`(list: NumbersMutableList) {
        repeat(10) {
            list.add(it)
        }
        assertEquals(5, list.get(5)  )
    }

    companion object {

        @JvmStatic
        fun mutableListSource() = listOf(NumbersArrayList())
    }
}