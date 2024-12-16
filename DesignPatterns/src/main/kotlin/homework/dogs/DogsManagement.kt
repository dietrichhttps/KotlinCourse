package homework.dogs

fun main() {
    DogsRepository.getInstance("qwerty").dogs
        .forEach(::println)
}