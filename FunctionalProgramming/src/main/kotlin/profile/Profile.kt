package profile

fun main() {
    showEmail()
}

fun filterCollection() {
    ProfilesRepository.profiles
        .filter { it.gender == Gender.MALE }
        .sortedByDescending { it.age }
        .forEach() { println(it) }
}

fun showEmail() {
    val id = readln().toInt()
    ProfilesRepository.profiles
        .find { it.id == id }
        ?.let { println(it.email) } ?: println("Not found")
}