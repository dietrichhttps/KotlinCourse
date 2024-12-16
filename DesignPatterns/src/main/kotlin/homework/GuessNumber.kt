package homework

import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    guessNumber()
}

fun guessNumber() {
    print("Enter number from 0 to 1_000_000_000: ")
    val input = readln().toInt()
    var isGuessed = false
    thread {
        var counter = 1
        while (!isGuessed) {
            println(counter++)
            Thread.sleep(1000)
        }
    }
    thread {
        while (true) {
            val number = Random.nextInt(1_000_001)
            if (number == input) {
                isGuessed = true
                println("I win. Your number is: $number")
                break
            }
        }
    }
}