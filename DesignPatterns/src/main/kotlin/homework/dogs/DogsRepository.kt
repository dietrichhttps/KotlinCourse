package homework.dogs

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observer.Observable
import observer.Observer
import java.io.File

class DogsRepository private constructor() : Observable<List<Dog>> {

    private val file = File("dogs.json")

    private val _dogs = loadDogs()

    override val currentValue
        get() = _dogs.toList()

    private val _observers = mutableListOf<Observer<List<Dog>>>()
    override val observers
        get() = _observers.toList()

    override fun registerObserver(observer: Observer<List<Dog>>) {
        _observers.add(observer)
        observer.onChanged(currentValue)
    }

    fun addOnDogsChangedListener(observer: Observer<List<Dog>>) {
        registerObserver(observer)
    }

    override fun unregisterObserver(observer: Observer<List<Dog>>) {
        TODO("Not yet implemented")
    }

    private fun loadDogs(): MutableList<Dog> = Json.decodeFromString(file.readText().trim())


    fun addDog(breed: String, name: String, weight: Double) {
        val id = _dogs.maxOf { it.id } + 1
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