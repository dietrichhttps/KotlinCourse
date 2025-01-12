package generics

fun main() {
    val box1 = Box(10)
    val box2 = Box(20)
    val sum = box1.value + box2.value
    println(sum)
}

fun <T> method(element: List<T>) {
//    if (element is List<Int>)
}

data class Box<T>(var value: T)