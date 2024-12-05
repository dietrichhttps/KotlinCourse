package corporation

abstract class Worker(
    val id: Int,
    val name: String,
    val age: Int,
    val positionType: PositionType
) {
    abstract fun work()

    open fun printInfo() {
        println("Id: $id Name: $name Age: $age Position: ${positionType.title}")
    }
}