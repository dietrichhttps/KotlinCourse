package test

import extensions.myApply

fun main() {
    exampleWith()
}

fun exampleWith() {
    with(mutableListOf<Int>()) {
        while (true) {
            print("Enter number or 0 to exit: ")
            val input = readln().toInt().takeIf { it != 0 } ?: break
            add(input)
        }
        println("Max: ${max()}")
        println("Min: ${min()}")
        this
    }.forEach {
        println(it)
    }

    fun exampleApply() {
        mutableListOf<Int>().myApply {
            while (true) {
                print("Enter number or 0 to exit: ")
                val input = readln().toInt().takeIf { it != 0 } ?: break
                add(input)
            }
        }.forEach {
            println(it)
        }
    }
}
