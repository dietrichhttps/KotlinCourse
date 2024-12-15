package test

fun main() {
    val dictionary = mutableMapOf<String, String>()
    dictionary["hello"] = "bonjour"
    for (entry in dictionary) {
        println(dictionary["hello"])
    }
}

