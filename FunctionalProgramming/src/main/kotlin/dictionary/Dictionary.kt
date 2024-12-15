package dictionary

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val file = File("dictionary.json")
    val content = file.readText().trim()
    val dictionary = Json.decodeFromString<List<Entry>>(content)
    showDescription(dictionary)
}

fun showDescription(dictionary: List<Entry>) {
    while (true) {
        print("Enter word or 0 to exit: ")
        val input = readln()
        if (input == "0") break
        dictionary.find { input.lowercase() == it.value }?.let { println(it.description) } ?: println("Word not found")
    }
}