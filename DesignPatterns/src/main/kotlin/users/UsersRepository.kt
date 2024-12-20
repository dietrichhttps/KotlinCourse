package users

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observer.MutableObservable
import observer.Observable
import java.io.File

class UsersRepository private constructor() {

    private val file = File("users.json")

    private val userList: MutableList<User> = loadUsers()

    private val _users = MutableObservable(userList.toList())
    val users: Observable<List<User>>
        get() = _users

    private val _oldestUser = MutableObservable(userList.maxBy { it.age })
    val oldestUser: Observable<User>
        get() = _oldestUser

    private fun loadUsers(): MutableList<User> = Json.decodeFromString(file.readText().trim())

    fun addUser(firstName: String, lastName: String, age: Int) {
        val id = userList.maxOf { it.id } + 1
        val user = User(age, firstName, id, lastName)
        userList.add(user)
        _users.currentValue = userList.toList()
        if (age > oldestUser.currentValue.age) {
            _oldestUser.currentValue = user
        }

    }

    fun deleteUser(id: Int) {
        userList.removeIf { id == it.id }
        _users.currentValue = userList.toList()
        val newOldest = userList.maxBy { it.age }
        if (newOldest != oldestUser.currentValue) {
            _oldestUser.currentValue = newOldest
        }
    }

    fun saveChanges() {
        file.writeText(Json.encodeToString(userList))
    }

    companion object {

        private val lock = Any()
        private var instance: UsersRepository? = null

        fun getInstance(password: String): UsersRepository {
            val correctPassword = File("password_users.txt").readText().trim()
            if (password != correctPassword) throw IllegalArgumentException("Wrong password")
            instance?.let { return it }
            synchronized(lock) {
                instance?.let { return it }
                return UsersRepository().also { instance = it }
            }
        }
    }
}

