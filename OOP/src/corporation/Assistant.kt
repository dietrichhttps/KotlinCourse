package corporation

data class Assistant(
    override val id: Int,
    override val name: String,
    override val age: Int,
    override val salary: Int
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

    override fun copy(salary: Int, age: Int): Assistant {
        return Assistant(this.id, this.name, age, salary)
    }

    override fun work() {
        println("I'm answering the phone now")
    }
}