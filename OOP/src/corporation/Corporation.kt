package corporation

fun main() {
    val director = WorkersRepository.findDirector() ?: throwDirectorIsRequired()
}

fun throwDirectorIsRequired(): Nothing {
    throw IllegalArgumentException("Director is required for this program. Please add it to the file workers.txt")
}