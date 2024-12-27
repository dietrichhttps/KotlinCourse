package collections

class NumbersLinkedList : NumbersMutableList {

    private var first: Node? = null
    private var last: Node? = null

    override var size: Int = 0
        private set

    private fun getNode(index: Int): Node {
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

    private fun unlink(node: Node) {
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


    override fun plus(number: Int) {
        add(number)
    }

    override fun minus(number: Int) {
        remove(number)
    }

    override fun add(number: Int) {
        val prevLast = last
        last = Node(prevLast, number)
        if (prevLast == null) {
            first = last
        } else {
            prevLast.next = last
        }
        size++
    }

    override fun add(index: Int, number: Int) {
        checkIndexForAdding(index)
        when (index) {
            size -> add(number)
            0 -> {
                val node = Node(null, number, first)
                first?.prev = node
                first = node
                size++
            }

            else -> {
                val before = getNode(index - 1)
                val after = before.next
                val node = Node(before, number, after)
                before.next = node
                after?.prev = node
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
        val node = getNode(index)
        unlink(node)
    }

    override fun remove(number: Int) {
        var node = first
        repeat(size) {
            if (node?.item == number) {
                node?.let { unlink(it) }
                return
            } else {
                node = node?.next
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
        var prev: Node? = null,
        val item: Int,
        var next: Node? = null
    )
}