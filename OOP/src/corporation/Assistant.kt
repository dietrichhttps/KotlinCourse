package corporation

class Assistant(
    id: Int,
    name: String,
    age: Int,
    salary: Int
) : Worker(
    id = id,
    name = name,
    age = age,
    salary = salary,
    Position.ASSISTANT
), Cleaner, Supplier {

    override fun clean() {
        println("I'm ${position.title}. I'm cleaning workplace...")
    }

    override fun supply() {
        println("I'm ${position.title}. I'm buying things...")
    }

    override fun work() {
        println("I'm answering the phone now")
    }
}