package corporation

abstract class Worker(
    val id: Int,
    val name: String,
    val age: Int,
    val position: Position
) {

    var salary = 150000
        set(value: Int) {
            if (value < field) {
                println("The new salary is too small...")
            } else {
                field = value
            }
        }

    abstract fun work()

    open fun printInfo() {
        println(this)
    }

    override fun toString(): String {
        return "Id: $id Name: $name Age: $age Position: ${position.title} Salary: $salary"
    }


}