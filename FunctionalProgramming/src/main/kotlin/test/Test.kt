package test

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun main() {
//    val file = File("items.json")
//    val items = readFromFile(file)
//    for (item in items) {
//        println(item)
//    }
    val file = File("books.json")
    val books = mutableListOf<Book>()
    while (true) {
        print("Enter title or 0 to exit: ")
        val title = readln()
        if (title == "0") break
        print("Enter authorName: ")
        val authorName = readln()
        print("Enter year: ")
        val year = readln().toInt()
        val book = Book(title, authorName, year)
        books.add(book)
    }
    file.writeText(Json.encodeToString(books))
    val booksFromFile = Json.decodeFromString<List<Book>>(file.readText().trim())
    for (book in booksFromFile) {
        println(book)
    }
}

fun readFromFile(file: File): List<Item> {
    val content = file.readText().trim()
    return Json.decodeFromString<List<Item>>(content)
}

fun writeToFile(file: File) {
    val items = mutableListOf<Item>()
    while (true) {
        print("Enter id or 0 to exit: ")
        val id = readln().toInt()
        if (id == 0) break
        print("Enter name: ")
        val name = readln()
        val item = Item(id, name)
        items.add(item)
    }
    val itemsAsString = Json.encodeToString(items)
    file.writeText(itemsAsString)
}