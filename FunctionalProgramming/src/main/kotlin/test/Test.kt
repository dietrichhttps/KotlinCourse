package test

import extensions.myApply

fun main() {
    mutableListOf<Int>().myApply {
        while (true) {
            print("Enter number or 0 to exit: ")
            val input = readln().toInt().takeIf { it != 0 } ?: break
            add(input)
        }
    }.forEach { println(it) }
}

