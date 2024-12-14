package profile

fun main() {
    ProfilesRepository.profiles
        .filter { it.age > 25 }
        .filter { it.gender == Gender.MALE }
        .filter { it.firstName.startsWith("A") }
        .filter { it.age < 30 }
        .transform { it.copy(age = it.age + 1) }
        .forEach { println(it) }
}

fun <R> List<Person>.transform(operation: (Person) -> R): List<R> {
    val result = mutableListOf<R>()
    for (person in this) {
        result.add(operation(person))
    }
    return result
}

fun List<Person>.filter(isSuitable: (Person) -> Boolean): List<Person> {
    val filteredProfiles = mutableListOf<Person>()
    for (profile in this) {
        if (isSuitable(profile)) {
            filteredProfiles.add(profile)
        }
    }
    return filteredProfiles
}
