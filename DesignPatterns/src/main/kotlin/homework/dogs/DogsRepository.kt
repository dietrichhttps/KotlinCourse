package homework.dogs

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class DogsRepository private constructor() {

    private val observers = mutableListOf<Display>()

    private val file = File("dogs.json")

    private val _dogs = loadDogs()
    val dogs
        get() = _dogs.toList()

    private fun loadDogs(): MutableList<Dog> = Json.decodeFromString(file.readText().trim())

    private fun notifyObservers() {
        observers.forEach { it.onChanged(dogs) }
    }

    fun registerObserver(observer: Display) {
        observers.add(observer)
        observer.onChanged(dogs)
    }

    fun addDog(breed: String, name: String, weight: Double) {
        val id = dogs.maxOf { it.id } + 1
        _dogs.add(Dog(breed, id, name, weight))
        notifyObservers()
    }

    fun deleteDog(id: Int) {
        _dogs.removeIf { it.id == id }
        notifyObservers()
    }

    fun saveChanges() {
        file.writeText(Json.encodeToString(_dogs))
    }

    companion object {

        private val lock = Any()
        private var instance: DogsRepository? = null

        fun getInstance(password: String): DogsRepository {

            val correctPassword = File("password_dogs.txt").readText().trim()
            if (correctPassword != password) throw IllegalArgumentException("Wrong password")
            instance?.let { return it }
            synchronized(lock) {
                instance?.let { return it }
                return DogsRepository().also { instance = it }
            }
        }
    }
}