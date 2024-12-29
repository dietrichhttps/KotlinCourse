package collections

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MyHashSetTest {

    private val numbers = MyHashSet<Item>()

    @Test
    fun `When added 1 element then size is 1`() {
        numbers.add(Item(1))
        assertEquals(1, numbers.size)
    }

    @Test
    fun `When added 100 element then size is 100`() {
        repeat(100) {
            numbers.add(Item(it))
        }
        assertEquals(100, numbers.size)
    }

    @Test
    fun `When added 10 similar elements then size is 1`() {
        repeat(10) {
            numbers.add(Item(1))
        }
        assertEquals(1, numbers.size)
    }

    @Test
    fun `When element added then method returns true`() {
        assertTrue {  numbers.add(Item(10))}
    }

    @Test
    fun `When element not added then method returns false`() {
        numbers.add(Item(10))
        assertFalse {  numbers.add(Item(10))}
    }

    @Test
    fun `When remove 1 element then size 99`() {
        repeat(100) {
            numbers.add(Item(it))
        }
        numbers.remove(Item(5))
        assertEquals(99, numbers.size)
    }

    @Test
    fun `When element removed then method contains returns false`() {
        repeat(100) {
            numbers.add(Item(it))
        }
        numbers.remove(Item(5))
        assertFalse { numbers.contains(Item(5)) }
    }

    @Test
    fun `When set contains element then method returns true`() {
        repeat(100) {
            numbers.add(Item(it))
        }
        assertTrue { numbers.contains(Item(5)) }
    }

    @Test
    fun `When set does not contain element then method returns false`() {
        repeat(100) {
            numbers.add(Item(it))
        }
        assertFalse{ numbers.contains(Item(1000)) }
    }

    @Test
    fun `When invoke method clear then size is 0`() {
        repeat(100) {
            numbers.add(Item(it))
        }
        numbers.clear()
        assertEquals(0, numbers.size)
    }

    @Test
    fun `When invoke method clear all elements is absent`() {
        repeat(10) {
            numbers.add(Item(it))
        }
        numbers.clear()
        repeat(10) {
            assertFalse { numbers.contains(Item(it)) }
        }
    }
}