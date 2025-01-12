package generics

import collections.MyArrayList
import collections.MyList
import collections.myListOf

fun main() {
    val workers = myListOf(Director("John"), Programmer("Alexis"), Programmer("Nicko"))
    workers
        .myFilterIsInstance<Programmer>()
        .forEach {it.writeCode()}
}

inline fun <reified R> MyList<*>.myFilterIsInstance(): MyList<R> {
    val result = MyArrayList<R>()
    for (element in this) {
        if (element is R) {
            result.add(element)
        }
    }
    return result
}

open class Worker(val name: String)

class Programmer(name: String) : Worker(name) {
    fun writeCode() {
        println("I'm writing the code...")
    }
}

class Director(name: String) : Worker(name)