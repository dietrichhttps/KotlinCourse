package homework.dogs

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observer.MutableObservable
import observer.Observable
import java.io.File

class DogsRepository private constructor() {

    private val file = File("dogs.json")

    private val dogList = loadDogs()

    private val _dogs = MutableObservable(dogList.toList())
    val dogs: Observable<List<Dog>>
        get() = _dogs

    private fun loadDogs(): MutableList<Dog> = Json.decodeFromString(file.readText().trim())

    fun addDog(breed: String, name: String, weight: Double) {
        val id = dogList.maxOf { it.id } + 1
        dogList.add(Dog(breed, id, name, weight))
        _dogs.currentValue = dogList.toList()
    }

    fun deleteDog(id: Int) {
        dogList.removeIf { it.id == id }
        _dogs.currentValue = dogList.toList()
    }

    fun saveChanges() {
        file.writeText(Json.encodeToString(dogList))
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