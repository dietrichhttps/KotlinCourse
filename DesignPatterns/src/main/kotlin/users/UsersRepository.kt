package users

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observer.Observer
import java.io.File

class UsersRepository private constructor() {

    private val file = File("users.json")

    private val observers = mutableListOf<Observer<List<User>>>()

    private val _users: MutableList<User> = loadUsers()
    val users
        get() = _users.toList()

    private fun loadUsers(): MutableList<User> = Json.decodeFromString(file.readText().trim())

    private fun notifyObservers() {
        observers.forEach { it.onChanged(users) }
    }

    fun registerObserver(observer: Observer<List<User>>) {
        observers.add(observer)
        observer.onChanged(users)
    }

    fun addUser(firstName: String, lastName: String, age: Int) {
        val id = users.maxOf { it.id } + 1
        _users.add(User(age, firstName, id, lastName))
        notifyObservers()
    }

    fun deleteUser(id: Int) {
        _users.removeIf { id == it.id }
        notifyObservers()
    }

    fun saveChanges() {
        file.writeText(Json.encodeToString(_users))
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

