package test

import java.io.File

val numbers = mutableListOf<Int>()

fun main() {
    val file = File("test.txt")
    val number = file.readText().trim().toInt()
    println(sum(5, number))
}

//fun sum(a: Int): Int {
//    val file = File("test.txt")
//    val number = file.readText().trim().toInt()
//    return a + number
//}

//fun sum(a: Int, b: Int): Int {
//    val result = a + b
//    numbers.add(result)
//    return result
//}

fun sum(a: Int, b: Int): Int {
    return a + b
}