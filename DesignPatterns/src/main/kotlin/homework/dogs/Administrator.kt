package homework.dogs

class Administrator {

    private val repository = DogsRepository.getInstance("qwerty")
    private val dogsInvoker = DogsInvoker

    fun work() {

        val operations = Operation.entries

        while (true) {
            print("Enter an operation: ")
            print(
                operations
                    .mapIndexed { index, operation -> "$index - ${operation.title}" }
                    .joinToString(", ", postfix = ": ")
            )
            val operationIndex = readln().toInt()
            val operation = operations[operationIndex]
            when (operation) {
                Operation.EXIT -> {
                    dogsInvoker.addCommand(AdministratorCommands.SaveChanges(repository))
                    break
                }

                Operation.ADD_DOG -> addDog()
                Operation.DELETE_DOG -> deleteDog()
            }
        }
    }

    private fun addDog() {
        print("Enter breed: ")
        val breed = readln()
        print("Enter name: ")
        val name = readln()
        print("Enter weight: ")
        val weight = readln().toDouble()
        dogsInvoker.addCommand(AdministratorCommands.AddDog(repository, breed, name, weight))
    }

    private fun deleteDog() {
        print("Enter id: ")
        val id = readln().toInt()
        DogsInvoker.addCommand(AdministratorCommands.DeleteDog(repository, id))
    }
}