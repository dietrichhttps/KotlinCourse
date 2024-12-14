package profile

import extensions.filter
import extensions.myForEach

fun main() {
    ProfilesRepository.profiles
        .filter { it.gender == Gender.MALE }
        .sortedByDescending { it.age }
        .myForEach { println(it)}
}
