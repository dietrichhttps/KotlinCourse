package corporation

abstract class Worker(
    val id: Int,
    val name: String,
    val age: Int,
    val salary: Int,
    val position: Position,
) {

    abstract fun copy(salary: Int = this.salary, age: Int = this.age): Worker

    abstract fun work()

    open fun printInfo() {
        println(this)
    }

    override fun toString(): String {
        return "Id: $id Name: $name Age: $age Position: ${position.title} Salary: $salary"
    }


}