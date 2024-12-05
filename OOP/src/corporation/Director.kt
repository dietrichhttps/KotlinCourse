package corporation

class Director(id: Int, name: String, age: Int) : Worker(id, name, age, Position.DIRECTOR), Supplier {

    override fun supply() {
        println("I'm ${position.title}. I'm buying things...")
    }

    override fun work() {
        println("I'm drinking coffee and reading reports")
    }
}