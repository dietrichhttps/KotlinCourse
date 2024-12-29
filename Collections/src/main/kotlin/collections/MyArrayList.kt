package collections

class MyArrayList<T>(initialCapacity: Int = INITIAL_CAPACITY) : MyMutableList<T> {

    private var elements = arrayOfNulls<Any>(initialCapacity)

    override var size: Int = 0
        private set

    private fun growIfNeeded() {
        if (elements.size == size) {
            val newArray = arrayOfNulls<Any>(elements.size * 2)
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    private fun checkIndexForAdding(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }


    override fun add(element: T) {
        growIfNeeded()
        elements[size] = element
        size++
    }

    override fun add(index: Int, element: T) {
        checkIndexForAdding(index)
        growIfNeeded()
        System.arraycopy(elements, index, elements, index + 1, size - index)
        elements[index] = element
        size++
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(elements, index + 1, elements, index, size - index - 1)
        size--
        elements[size] = null
    }

    override fun remove(element: T) {
        for (i in 0 until size) {
            if (elements[i] == element) {
                removeAt(i)
                return
            }
        }
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return elements[index] as T
    }

    override fun contains(element: T): Boolean {
        for (i in 0 until size) {
            if (elements[i] == element) {
                return true
            }
        }
        return false
    }

    override fun clear() {
        elements = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    companion object {

        private const val INITIAL_CAPACITY = 10
    }
}