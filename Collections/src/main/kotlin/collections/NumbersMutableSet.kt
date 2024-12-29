package collections

interface NumbersMutableSet {

    val size: Int

    fun add(number: Int): Boolean

    fun remove(number: Int)

    fun contains(number: Int): Boolean

    fun clear()
}