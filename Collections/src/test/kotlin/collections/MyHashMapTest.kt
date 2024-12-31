package collections

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class MyHashMapTest {

    val elements = MyHashMap<Int, String>()

    @Test
    fun `When put element with non existent key then size increase`() {
        repeat(100) {
            elements.put(it, "Number: $it")
        }
        elements.put(100, "Number: 100")
        assertEquals(101, elements.size)
    }

    @Test
    fun `When put element with existent key then size does not increase`() {
        repeat(100) {
            elements.put(it, "Number: $it")
        }
        elements.put(99, "Number: 99")
        assertEquals(100, elements.size)
    }

    @Test
    fun `When put element with existent key then value overwrites`() {
        repeat(100) {
            elements.put(it, "Number: $it")
        }
        val oldValue = elements.put(0, "New value")
        val newValue = elements[0]
        assertFalse { oldValue == newValue }
    }

    @Test
    fun `Keys size equals elements size`() {
        repeat(100) {
            elements.put(it, "Number: $it")
        }
        assertTrue { elements.keys.size == elements.size}
    }

    @Test
    fun `Values size equals elements size`() {
        repeat(100) {
            elements.put(it, "Number: $it")
        }
        assertTrue { elements.values.size == elements.size}
    }

    @Test
    fun `When remove element then size decrease`() {
        repeat(100) {
            elements.put(it, "Number: $it")
        }
        elements.remove(0)
        assertEquals(99, elements.size)
    }

    @Test
    fun `When remove element with existent key then method returns value`() {
        repeat(100) {
            elements.put(it, "Number: $it")
        }
        val valueToRemove = elements[0]
        val removedValue = elements.remove(0)
        assertTrue {valueToRemove == removedValue}
    }
}