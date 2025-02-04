package collections

class MyLinkedList<T> : MyMutableList<T> {

    private var first: Node<T>? = null
    private var last: Node<T>? = null

    private var modCount = 0

    override var size: Int = 0
        private set

    private fun getNode(index: Int): Node<T> {
        val node = when  {
            index == 0 -> first!!
            index == size - 1 -> last!!
            index < size / 2 -> {
                var node = first
                repeat(index) {
                    node = node?.next
                }
                node!!
            }
            else -> {
                var node = last
                repeat(size - index - 1) {
                    node = node?.prev
                }
                node!!
            }
        }
        return node
    }

    private fun unlink(node: Node<T>) {
        val before = node.prev
        val after = node.next
        before?.next = after
        after?.prev = before
        if (after == null) {
            last = before
        }
        if (before == null) {
            first = after
        }
        size--
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


    override fun plus(element: T) {
        add(element)
    }

    override fun minus(element: T) {
        remove(element)
    }

    override fun add(element: T): Boolean {
        modCount++
        val prevLast = last
        last = Node(prevLast, element)
        if (prevLast == null) {
            first = last
        } else {
            prevLast.next = last
        }
        size++
        return true
    }

    override fun add(index: Int, element: T) {
        modCount++
        checkIndexForAdding(index)
        when (index) {
            size -> add(element)
            0 -> {
                val node = Node(null, element, first)
                first?.prev = node
                first = node
                size++
            }

            else -> {
                val before = getNode(index - 1)
                val after = before.next
                val node = Node(before, element, after)
                before.next = node
                after?.prev = node
                size++
            }
        }
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return getNode(index).item
    }

    override fun removeAt(index: Int) {
        modCount++
        checkIndex(index)
        val node = getNode(index)
        unlink(node)
    }

    override fun remove(element: T) {
        modCount++
        var node = first
        repeat(size) {
            if (node?.item == element) {
                node?.let { unlink(it) }
                return
            } else {
                node = node?.next
            }
        }
    }

    override fun contains(element: T): Boolean {
        var node = first
        while (node != null) {
            if (node.item == element) {
                return true
            }
            node = node.next
        }
        return false
    }

    override fun clear() {
        modCount++
        first = null
        last = null
        size = 0
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {

            private var nextNode = first
            private val currentModCount = modCount

            override fun hasNext(): Boolean {
                return nextNode != null
            }

            override fun next(): T {
                if (currentModCount != modCount) throw ConcurrentModificationException()
                return nextNode?.item!!.also {
                    nextNode = nextNode?.next
                }
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }
    }

    class Node<T>(
        var prev: Node<T>? = null,
        val item: T,
        var next: Node<T>? = null
    )
}