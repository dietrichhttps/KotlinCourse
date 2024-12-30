package collections

import kotlin.random.Random

fun main() {
    val elements = sortedSetOf<Item>({ o1, o2 ->
        when {
            o1.value > o2.value -> 1
            o1.value < o2.value -> -1
            else -> 0
        }
    })
        .apply {
            repeat(100) {
                add(Item(Random.nextInt(100)))
            }
        }

    for (element in elements) {
        println(element)
    }
}