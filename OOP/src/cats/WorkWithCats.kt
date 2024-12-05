package cats

fun main() {
    val cat = Cat()
    val lion = Lion()
    val animals = listOf(cat, lion)
    for (animal in animals) {
        animal.eat()
    }
}
