package collections

class MyArrayList<T>(initialCapacity: Int = INITIAL_CAPACITY) : MyMutableList<T> {

    private var elements = arrayOfNulls<Any>(initialCapacity)

    private var modCount = 0

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


    override fun add(element: T): Boolean {
        modCount++
        growIfNeeded()
        elements[size] = element
        size++
        return true
    }

    override fun add(index: Int, element: T) {
        modCount++
        checkIndexForAdding(index)
        growIfNeeded()
        System.arraycopy(elements, index, elements, index + 1, size - index)
        elements[index] = element
        size++
    }

    override fun removeAt(index: Int) {
        modCount++
        checkIndex(index)
        System.arraycopy(elements, index + 1, elements, index, size - index - 1)
        size--
        elements[size] = null
    }

    override fun remove(element: T) {
        modCount++
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
        modCount++
        elements = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {

            private var nextIndex = 0
            private val currentModCont = modCount

            override fun hasNext(): Boolean {
                return nextIndex < size
            }

            override fun next(): T {
                if (currentModCont != modCount) throw ConcurrentModificationException()
                return elements[nextIndex++] as T
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }
    }

    companion object {

        private const val INITIAL_CAPACITY = 10
    }
}