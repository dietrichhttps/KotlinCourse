package collections

fun main() {
    val immutable = myListOf(1, 2, 3, 4, 5)
    (immutable as MyMutableList<Int>).add(100)
    immutable.forEach(::println)
}