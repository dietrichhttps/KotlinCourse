package collections

interface MyList<out T> : MyCollection<T> {

    override val size: Int

    operator fun get(index: Int): T

    override fun contains(element: @UnsafeVariance T): Boolean
}