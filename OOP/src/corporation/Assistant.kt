package corporation

class Assistant(id: Int, name: String, age: Int) : Worker(id, name, age, Position.ASSISTANT), Cleaner, Supplier {

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