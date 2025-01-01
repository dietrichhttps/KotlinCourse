package collections

class MyHashSet<T> : MyMutableSet<T> {

    private val map = MyHashMap<T, Any>()

    override val size
        get() = map.size

    override fun add(element: T): Boolean {
        return map.put(element, PRESENT) == null
    }

    override fun remove(element: T) {
        map.remove(element)
    }

    override fun contains(element: T): Boolean {
        return map.containsKey(element)
    }

    override fun clear() {
        map.clear()
    }

    override fun iterator(): MutableIterator<T> = map.keyIterator()

    companion object {

        private val PRESENT = Any()
    }
}