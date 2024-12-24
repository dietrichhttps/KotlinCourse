package collections

interface NumbersMutableList {

    val size: Int

    operator fun plus(number: Int) {
        add(number)
    }

    operator fun minus(number: Int) {
        remove(number)
    }

    fun add(number: Int)

    fun add(index: Int, number: Int)

    operator fun get(index: Int): Int

    fun removeAt(index: Int)

    fun remove(number: Int)

    fun contains(number: Int): Boolean

    fun clear()
}