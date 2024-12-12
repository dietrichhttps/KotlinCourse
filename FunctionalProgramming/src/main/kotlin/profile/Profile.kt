package profile

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val profiles = ProfilesRepository.profiles
    profiles.listIterator().forEach { println(it) }

}
