package corporation

abstract class Worker(
    open val id: Int,
    open val name: String,
    open val age: Int,
    open val salary: Int,
    val position: Position,
) {

    abstract fun copy(salary: Int = this.salary, age: Int = this.age): Worker

    abstract fun work()

    open fun printInfo() {
        println(this)
    }
}