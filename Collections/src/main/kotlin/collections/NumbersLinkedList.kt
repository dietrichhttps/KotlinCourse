package collections

class NumbersLinkedList : NumbersMutableList {

    private var first: Node? = null
    private var last: Node? = null

    override var size: Int = 0
        private set

    private fun getNode(index: Int): Node {
        val node = when (index) {
            0 -> first!!
            size - 1 -> last!!
            else -> {
                var node = first
                repeat(index) {
                    node = node?.next
                }
                node!!
            }
        }
        return node
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


    override fun plus(number: Int) {
        add(number)
    }

    override fun minus(number: Int) {
        remove(number)
    }

    override fun add(number: Int) {
        if (size == 0) {
            val node = Node(number)
            first = node
            last = node
            size++
            return
        }
        val newElement = Node(number)
        last?.next = newElement
        last = newElement
        size++
    }

    override fun add(index: Int, number: Int) {
        checkIndexForAdding(index)
        when (index) {
            size -> add(number)
            0 -> {
                val newNode = Node(number, first)
                first = newNode
                size++
            }

            else -> {
                val before = getNode(index - 1)
                val after = before.next
                val newNode = Node(number, after)
                before.next = newNode
                size++
            }
        }
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).item
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        when {
            index == 0 && size == 1 -> clear()
            index == 0 -> first = first?.next
            else -> {
                val before = getNode(index - 1)
                val after = before.next?.next
                before.next = after
                if (after == null) {
                    last = before
                }
            }
        }
        size--
    }

    override fun remove(number: Int) {
        if (first?.item == number) {
            removeAt(0)
            return
        }
        var before = first
        repeat(size) {
            val node = before?.next
            if (node?.item == number) {
                val after = node.next
                before?.next = after
                if (after == null) {
                    last = before
                }
                size--
                return
            } else {
                before = before?.next
            }
        }
    }

    override fun contains(number: Int): Boolean {
        var node = first
        while (node != null) {
            if (node.item == number) {
                return true
            }
            node = node.next
        }
        return false
    }

    override fun clear() {
        first = null
        last = null
        size = 0
    }

    class Node(
        val item: Int,
        var next: Node? = null
    )
}