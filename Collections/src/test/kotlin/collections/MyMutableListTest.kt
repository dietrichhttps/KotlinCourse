package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MyMutableListTest {

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 1 element then size is 1`(list: MyMutableList<Item>) {
        list.add(Item(0))
        assertEquals(1, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to first position then it is in first position`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        list.add(0, Item(1000))
        assertEquals(Item(1000), list[0])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to last position then it is in last position`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        list.add(100, Item(1001))
        assertEquals(Item(1001), list[100])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to 50th position then it is in 50th position`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        list.add(50, Item(1002))
        assertEquals(Item(1002), list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 elements then size is 10`(list: MyMutableList<Item>) {
        repeat(10) {
            list.add(Item(it))
        }
        assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 100 elements then size is 100`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        assertEquals(100, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 5th element then result is correct`(list: MyMutableList<Item>) {
        repeat(10) {
            list.add(Item(it))
        }
        assertEquals(Item(5), list[5])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 50th element then result is correct`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        assertEquals(Item(50), list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element removed then size decreased`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        list.removeAt(50)
        assertEquals(99, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed 50th element next value at this position`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        list.removeAt(50)
        assertEquals(Item(51), list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed value 50 element next value at this position`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        list.remove(Item(50))
        assertEquals(Item(51), list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list is cleared then size is 0`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        list.clear()
        assertEquals(0, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list contains element then method returns true`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        assertTrue { list.contains(Item(50)) }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list does not contain element then method returns false`(list: MyMutableList<Item>) {
        repeat(100) {
            list.add(Item(it))
        }
        assertFalse { list.contains(Item(100000)) }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method get invoked with wrong index then exception is thrown`(list: MyMutableList<Item>) {
        repeat(10) {
            list.add(Item(it))
        }
        assertThrows<IndexOutOfBoundsException> { list[10] }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method get invoked with negative index then exception is thrown`(list: MyMutableList<Item>) {
        repeat(10) {
            list.add(Item(it))
        }
        assertThrows<IndexOutOfBoundsException> { list[-1] }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method add invoked with wrong index then exception is thrown`(list: MyMutableList<Item>) {
        repeat(10) {
            list.add(Item(it))
        }
        assertThrows<IndexOutOfBoundsException> { list.add(11, Item(1000)) }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method add invoked with negative index then exception is thrown`(list: MyMutableList<Item>) {
        repeat(10) {
            list.add(Item(it))
        }
        assertThrows<IndexOutOfBoundsException> { list.add(-1, Item(1000)) }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method removeAt invoked with wrong index then exception is thrown`(list: MyMutableList<Item>) {
        repeat(10) {
            list.add(Item(it))
        }
        assertThrows<IndexOutOfBoundsException> { list.removeAt(10) }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method removeAt invoked with negative index then exception is thrown`(list: MyMutableList<Item>) {
        repeat(10) {
            list.add(Item(it))
        }
        assertThrows<IndexOutOfBoundsException> { list.removeAt(-1) }
    }

    companion object {

        @JvmStatic
        fun mutableListSource() = listOf(MyArrayList<Item>(), MyLinkedList())
    }
}