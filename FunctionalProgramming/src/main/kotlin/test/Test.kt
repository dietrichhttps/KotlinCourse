package test

fun main() {
    val a = readln().toInt()
    println(a.isPositive())
}

fun Int.isPositive() = this > 0