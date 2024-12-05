package corporation

class Consultant(id: Int, name: String, age: Int) : Worker(id, name, age, Position.CONSULTANT), Cleaner {

    override fun clean() {
        println("I'm ${position.title}. I'm cleaning workplace...")
    }

    override fun work() {
        println("I'm consulting clients")
    }
}