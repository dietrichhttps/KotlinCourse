package profile

fun main() {
    val profiles = ProfilesRepository.profiles
    var filteredProfiles = filter(
        profiles, object : Condition {
            override fun isSuitable(person: Person): Boolean {
                return person.age > 25
            }
        }
    )
    filteredProfiles = filter(filteredProfiles, object: Condition {
        override fun isSuitable(person: Person): Boolean {
            return person.gender == Gender.MALE
        }
    })
    filteredProfiles = filter(filteredProfiles, object: Condition {
        override fun isSuitable(person: Person): Boolean {
            return person.firstName.startsWith("A")
        }
    })
    for (profile in filteredProfiles) {
        println(profile)
    }
}

fun filter(profiles: List<Person>, condition: Condition): List<Person> {
    val filteredProfiles = mutableListOf<Person>()
    for (profile in profiles) {
        if (condition.isSuitable(profile)) {
            filteredProfiles.add(profile)
        }
    }
    return filteredProfiles
}
