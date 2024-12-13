package profile

import org.w3c.dom.ls.LSOutput

fun main() {
    val profiles = ProfilesRepository.profiles
    var filteredProfiles = filter(profiles) { it.age > 25 }
    filteredProfiles = filter(filteredProfiles) { it.gender == Gender.MALE }
    filteredProfiles = filter(filteredProfiles) { it.firstName.startsWith("A") }
    filteredProfiles.forEach { println(it) }
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
