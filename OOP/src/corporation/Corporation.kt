package corporation

fun main() {
//    val accountant = Accountant(100,"Mike", 60, 50000)
//    accountant.work()

    val workers = mutableSetOf<Worker>()
    val a = Director(0,"", 0, 0)
    val b = Director(0,"", 0, 0)
    println(a.hashCode())
    println(b.hashCode())
    println(a == b)
    workers.add(a)
    workers.add(b)
    for (worker in workers) {
        worker.printInfo()
    }
}
