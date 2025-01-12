package generics

fun main() {

    val a = Container(10)
    val b = Container("Hello")
    val c = Container<Any>()

    copy(a, c)
    copy(b, c)

//    val programmer = Container(Programmer("John"))
//    val director = Container(Director("Max"))
//    val worker = Container<Worker>()

//    copy(programmer, worker)
//
//    println(programmer.value)
//    println(director.value)
//    println(worker.value)
}

fun <T> copy(src: Container<T>, dst: Container<in T>) {
    dst.value = src.value
}

data class Container<T> (var value: T? = null)