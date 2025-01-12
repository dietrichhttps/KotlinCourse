package generics

import collections.MyList
import collections.myListOf

fun main() {
    val worker = Programmer("John")
    showName(worker)

    val workers: MyList<Programmer> = myListOf(Programmer("Nick"), Programmer("Ivan"))
    showCount(workers)
}

private fun showCount(workers: MyList<Worker>) {
    println(workers.size)
}

private fun showName(worker: Worker) {
    println(worker.name)
}

open class Worker(val name: String)

class Programmer(name: String) : Worker(name)

class Director(name: String) : Worker(name)