package collections

interface MyMutableCollection<T> : Iterable<T> {

    val size: Int

    fun add(element: T): Boolean

    fun remove(element: T)

    fun contains(element: T): Boolean

    fun clear()
}