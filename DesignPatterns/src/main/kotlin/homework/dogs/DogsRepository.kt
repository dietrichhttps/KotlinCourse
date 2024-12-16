package homework.dogs

import kotlinx.serialization.json.Json
import java.io.File

class DogsRepository private constructor() {

    private val file = File("dogs.json")

    private val _dogs = loadDogs()
    val dogs
        get() = _dogs.toList()

    private fun loadDogs(): MutableList<Dog> = Json.decodeFromString(file.readText().trim())

    companion object {

        private var instance: DogsRepository? = null

        fun getInstance(password: String): DogsRepository {

            val correctPassword = File("password_dogs.txt").readText().trim()
            if (correctPassword != password) throw IllegalArgumentException("Wrong password")

            if (instance == null) {
                instance = DogsRepository()
            }
            return instance!!
        }
    }
}