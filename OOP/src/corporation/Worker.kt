package corporation

abstract class Worker(
    val id: Int,
    val name: String,
    val age: Int,
    private var salary: Int,
    val position: Position,
) {

    fun getSalary() = salary

    fun setSalary(value: Int) {
        if (value < salary) {
            println("The new salary is too small...")
        } else {
            salary = value
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