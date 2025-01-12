package data

fun main() {
//    val (english, french) = MyPair("", "")
    val dictionary = listOf(
        "Hello" myTo "Bonjour",
        "Thank You" myTo "Merci",
    )
    for ((first, second) in dictionary) {
        println("$first - $second")
    }
}

data class MyPair<F, S>(val first: F, val second: S)

infix fun <F, S> F.myTo(second: S) = MyPair(this, second)