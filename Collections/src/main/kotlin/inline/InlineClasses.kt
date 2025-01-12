package inline

fun main() {
    val user = User(UserId(0), "John")
    user.id.showValue()
    println(user)
}

data class User(val id: UserId, val name: String)

@JvmInline
value class UserId(val value: Int) {

    fun showValue() {
        println(value)
    }
}