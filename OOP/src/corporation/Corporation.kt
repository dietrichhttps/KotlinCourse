package corporation

fun main() {
    val accountant = Accountant(100,"Mike", 60, 50000)
    val workers = WorkersRepository.workers
    accountant.work()
}
