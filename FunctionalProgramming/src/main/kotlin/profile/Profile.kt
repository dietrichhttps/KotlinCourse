package profile

import extensions.filter

fun main() {
    ProfilesRepository.profiles
        .filter { it.age > 25 }
        .filter { it.gender == Gender.MALE }
        .filter { it.firstName.startsWith("A") }
        .filter { it.age < 30 }
        .toSet()
        .map { it.copy(age = it.age + 1) }
        .forEach { println(it) }
}
