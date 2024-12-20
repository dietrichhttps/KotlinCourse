package users

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observer.Observable
import observer.Observer
import java.io.File

class UsersRepository private constructor() : Observable<List<User>> {

    private val file = File("users.json")

    private val _observers = mutableListOf<Observer<List<User>>>()
    override val observers
        get() = _observers.toList()

    private val _users: MutableList<User> = loadUsers()

    override val currentValue: List<User>
        get() = _users.toList()

    private fun loadUsers(): MutableList<User> = Json.decodeFromString(file.readText().trim())

    override fun registerObserver(observer: Observer<List<User>>) {
        _observers.add(observer)
        observer.onChanged(currentValue)
    }

    override fun unregisterObserver(observer: Observer<List<User>>) {
        _observers.remove(observer)
    }

    fun addOnUsersChangedListener(observer: Observer<List<User>>) {
        registerObserver(observer)
    }

    fun addUser(firstName: String, lastName: String, age: Int) {
        val id = _users.maxOf { it.id } + 1
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

