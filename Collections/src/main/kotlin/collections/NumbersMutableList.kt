package collections

interface NumbersMutableList {

    val size: Int

    fun add(number: Int)

    fun add(index: Int, number: Int)

    fun get(index: Int): Int

    fun removeAt(index: Int)

    fun remove(number: Int)

    fun contains(number: Int): Boolean

    fun clear()
}