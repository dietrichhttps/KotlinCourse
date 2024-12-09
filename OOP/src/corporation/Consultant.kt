package corporation

data class Consultant(
    override val id: Int,
    override val name: String,
    override val age: Int,
    override val salary: Int
) : Worker(
    id = id,
    name = name,
    age = age,
    salary = salary,
    position = Position.CONSULTANT
), Cleaner {

    override fun clean() {
        println("I'm ${position.title}. I'm cleaning workplace...")
    }

    override fun copy(salary: Int, age: Int): Consultant {
        return Consultant(this.id, this.name, age, salary)
    }

    override fun work() {
        println("I'm consulting clients")
    }
}