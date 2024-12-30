package collections

import kotlin.random.Random

fun main() {
    val elements = MyLinkedList<Item>().apply {
        repeat(100) {
            add(Item(Random.nextInt(1000)))
        }
    }

    for (element in elements) {
        println(element)
    }
}