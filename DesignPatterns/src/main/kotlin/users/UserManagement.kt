package users

fun main() {
    UsersRepository.getInstance("qwerty").users
        .forEach(::println)
}