package collections

import kotlin.math.abs

class NumbersHashSet : NumbersMutableSet {

    var elements = arrayOfNulls<Node>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(number: Int): Boolean {
        if (size >= elements.size * LOAD_FACTOR) {
            increaseArray()
        }
        return add(number, elements). also { added ->
            if (added) size++
        }
    }

    private fun add(number: Int, array: Array<Node?>): Boolean {
        val newElement = Node(number)
        val position = getElementPosition(number, array.size)
        var existedElement = array[position]
        if (existedElement == null) {
            array[position] = newElement
            return true
        } else {
            while (true) {
                if (existedElement?.item == number) return false
                if (existedElement?.next == null) {
                    existedElement?.next = newElement
                    return true
                }
                existedElement = existedElement.next
            }
        }
    }

    override fun remove(number: Int) {
        TODO("Not yet implemented")
    }

    override fun contains(number: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    private fun getElementPosition(number: Int, arraySize: Int): Int {
        return abs(number % arraySize)
    }

    private fun increaseArray() {
        val newArray = arrayOfNulls<Node>(elements.size * 2)
        for (node in elements) {
            var currentElement = node
            while (currentElement != null) {
                add(currentElement.item, newArray)
                currentElement = currentElement.next
            }
        }
        elements = newArray
    }

    data class Node(
        val item: Int,
        var next: Node? = null
    )

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}