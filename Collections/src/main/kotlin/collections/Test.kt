package collections

import kotlin.random.Random

fun main() {
    NumbersHashSet<Item>().apply {
        repeat(100) {
            add(Item(Random.nextInt(1000)))
        }
        elements.forEach(::println)
    }
}