package collections

import kotlin.math.abs

class MyHashMap<K, V> : MyMutableMap<K, V> {

    private var elements = arrayOfNulls<Node<K, V>>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override val keys: MySet<K>
        get() = MyHashSet<K>().apply {
            foreach { add(it.key) }
        }

    override val values: MyCollection<V>
        get() = MyArrayList<V>().apply {
            foreach { add(it.value) }
        }

    override fun put(key: K, value: V): V? {
        if (size >= elements.size * LOAD_FACTOR) {
            increaseArray()
        }
        return put(key, value, elements).also { oldValue ->
            if (oldValue == null ) size++
        }
    }

    private fun put(key: K, value: V, array: Array<Node<K, V>?>): V? {
        val newElement = Node(key, value)
        val position = getElementPosition(key, array.size)
        var existedElement = array[position]
        if (existedElement == null) {
            array[position] = newElement
            return null
        } else {
            while (true) {
                if (existedElement?.key == key) {
                    return existedElement?.value.also {
                        existedElement?.value = value
                    }
                } else {
                    if (existedElement?.next == null) {
                        existedElement?.next = newElement
                        return null
                    }
                    existedElement = existedElement.next
                }
            }
        }
    }

    override fun remove(key: K): V? {
        val position = getElementPosition(key, elements.size)
        val existedElement = elements[position] ?: return null
        if (existedElement.key == key) {
            elements[position] = existedElement.next
            size--
            return existedElement.value
        }
        var before: Node<K, V>? = existedElement
        while (before?.next != null) {
            val removingElement = before.next
            if (removingElement?.key == key) {
                before.next = removingElement?.next
                size--
                return removingElement?.value
            }
            before = before.next
        }
        return null
    }

    override fun get(key: K): V? {
        val position = getElementPosition(key, elements.size)
        var existedElement = elements[position]
        while (existedElement != null) {
            if (existedElement.key == key) return existedElement.value
            existedElement = existedElement.next
        }
        return null
    }

    override fun containsKey(key: K): Boolean {
        val position = getElementPosition(key, elements.size)
        var existedElement = elements[position]
        while (existedElement != null) {
            if (existedElement.key == key) return true
            existedElement = existedElement.next
        }
        return false
    }

    override fun containsValue(value: V): Boolean {
        foreach {
            if (it.value == value) return true
        }
        return false
    }

    override fun clear() {
        elements = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    private fun getElementPosition(key: K, arraySize: Int): Int {
        return abs(key.hashCode() % arraySize)
    }

    private fun increaseArray() {
        val newArray = arrayOfNulls<Node<K, V>>(elements.size * 2)
        for (node in elements) {
            var currentElement = node
            while (currentElement != null) {
                put(currentElement.key, currentElement.value, newArray)
                currentElement = currentElement.next
            }
        }
        elements = newArray
    }

    private inline fun foreach(operation: (Node<K, V>) -> Unit) {
        for (node in elements) {
            var currentElement = node
            while (currentElement != null) {
                operation(currentElement)
                currentElement = currentElement.next
            }
        }
    }

    data class Node<K, V>(
        val key: K,
        var value: V,
        var next: Node<K, V>? = null
    )

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}