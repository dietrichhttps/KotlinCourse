package corporation

open class Worker(
    val id: Int,
    val name: String,
    val age: Int,
    val positionType: PositionType
) {
    open fun work() {
        println("I'm working")
    }

    open fun printInfo() {
        println("Id: $id Name: $name Age: $age Position: ${positionType.title}")
    }
}