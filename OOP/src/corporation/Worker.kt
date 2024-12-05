package corporation

abstract class Worker(
    val id: Int,
    val name: String,
    val age: Int,
    val position: Position
) {
    abstract fun work()

    open fun printInfo() {
        println("Id: $id Name: $name Age: $age Position: ${position.title}")
    }
}