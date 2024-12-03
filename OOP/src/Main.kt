import corporation.*

fun main() {
    val director = Director("Walter", 52)
    val consultant = Consultant("Soul", 46)
    val assistant = Assistant("Jesse", 30)
    val accountant = Accountant("Mike", 60)
    val workers = listOf(director, consultant, assistant, accountant)
    for (worker in workers) {
        worker.work()
    }
}