package collections

import kotlin.time.measureTime

fun main() {
    val numbers = mutableListOf<Int>()
    val time = measureTime {
        repeat(1_000_000) {
            numbers.add(0, it)
        }
    }
    println("Time: $time")
}