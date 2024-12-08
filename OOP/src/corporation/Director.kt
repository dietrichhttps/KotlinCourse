package corporation

class Director(
    id: Int,
    name: String,
    age: Int,
    salary: Int
) : Worker(
    id = id,
    name = name,
    age = age,
    salary = salary,
    position = Position.DIRECTOR
), Supplier {

    override fun supply() {
        println("I'm ${position.title}. I'm buying things...")
    }

    override fun copy(salary: Int, age: Int): Director {
        return Director(this.id, this.name, age, salary)
    }

    override fun work() {
        println("I'm drinking coffee and reading reports")
    }
}