package profile

fun main() {
    val profiles = ProfilesRepository.profiles
    var filteredProfiles = filter(profiles) { it.age > 25 }
    filteredProfiles = filter(filteredProfiles) { it.gender == Gender.MALE }
    filteredProfiles = filter(filteredProfiles) { it.firstName.startsWith("A") }
    val transformed = transform(filteredProfiles) { it.copy(age = it.age + 1) }
    transformed.forEach { println(it) }
}

fun <R> transform(profiles: List<Person>, operation: (Person) -> R): List<R> {
    val result = mutableListOf<R>()
    for (person in profiles) {
        result.add(operation(person))
    }
    return result
}

fun filter(profiles: List<Person>, isSuitable: (Person) -> Boolean): List<Person> {
    val filteredProfiles = mutableListOf<Person>()
    for (profile in profiles) {
        if (isSuitable(profile)) {
            filteredProfiles.add(profile)
        }
    }
    return filteredProfiles
}
