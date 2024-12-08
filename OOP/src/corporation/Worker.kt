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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Worker

        if (id != other.id) return false
        if (age != other.age) return false
        if (salary != other.salary) return false
        if (name != other.name) return false
        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + age
        result = 31 * result + salary
        result = 31 * result + name.hashCode()
        result = 31 * result + position.hashCode()
        return result
    }


}