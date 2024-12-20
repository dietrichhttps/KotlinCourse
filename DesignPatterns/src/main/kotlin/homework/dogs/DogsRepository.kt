package homework.dogs

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observer.MutableObservable
import observer.Observable
import observer.Observer
import java.io.File

class DogsRepository private constructor() {

    private val file = File("dogs.json")

    private val _dogs = loadDogs()

    val dogs = MutableObservable(_dogs.toList())

    private fun loadDogs(): MutableList<Dog> = Json.decodeFromString(file.readText().trim())

    fun addDog(breed: String, name: String, weight: Double) {
        val id = _dogs.maxOf { it.id } + 1
        _dogs.add(Dog(breed, id, name, weight))
        dogs.currentValue = _dogs.toList()
    }

    fun deleteDog(id: Int) {
        _dogs.removeIf { it.id == id }
        dogs.currentValue = _dogs.toList()
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