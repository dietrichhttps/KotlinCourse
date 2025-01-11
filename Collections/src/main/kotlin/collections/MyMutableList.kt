package collections

interface MyMutableList<T> : MyList<T>, MyMutableCollection<T> {

    override val size: Int

    operator fun plus(element: T) {
        add(element)
    }

    operator fun minus(element: T) {
        remove(element)
    }

    override fun add(element: T): Boolean

    fun add(index: Int, element: T)

    override operator fun get(index: Int): T

    fun removeAt(index: Int)

    override fun remove(element: T)

    override fun contains(element: T): Boolean

    override fun clear()
}