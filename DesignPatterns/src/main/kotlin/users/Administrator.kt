package users

class Administrator {

    private val repository = UsersRepository.getInstance("qwerty")
    private val usersInvoker = UsersInvoker

    fun work() {
        val operations = Operation.entries
        while (true) {
            print("Enter an operation: ")
            print(
                operations
                    .mapIndexed { index, code -> "$index - ${code.title}" }
                    .joinToString(", ", postfix = ": ")
            )
            val operationCodeIndex = readln().toInt()
            val operationCode = operations[operationCodeIndex]
            when (operationCode) {
                Operation.EXIT -> {
                    repository.saveChanges()
                    break
                }
                Operation.ADD_USER -> addUser()
                Operation.DELETE_USER -> deleteUser()
            }
        }
    }

    private fun addUser() {
        print("Enter firstname: ")
        val firstName = readln()
        print("Enter lastname: ")
        val lastName = readln()
        print("Enter age: ")
        val age = readln().toInt()
        usersInvoker.addCommand {
            repository.addUser(firstName, lastName, age)
        }
    }

    private fun deleteUser() {
        print("Enter id: ")
        val id = readln().toInt()
        usersInvoker.addCommand {
            repository.deleteUser(id)
        }
    }
}