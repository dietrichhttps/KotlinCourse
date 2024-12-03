package files

import java.io.File

fun main() {
    toDoListCli()
}

fun toDoListCli() {
    val file = File("todolist.txt")
    val operationCodes = OperationCode.entries
    while (true) {
        printInstructions(operationCodes)
        val operationCodeIndex = readln().toInt()
        val operationCode = operationCodes[operationCodeIndex]
        when (operationCode) {
            OperationCode.EXIT -> break
            OperationCode.ADD_NEW_ITEM -> addNewItem(file)
            OperationCode.SHOW_ALL_ITEMS -> showAllItems(file)
            OperationCode.DELETE_ALL_ITEMS -> deleteAllItems(file)
        }
    }
}

fun printInstructions(operationCodes: List<OperationCode>) {
    print("Enter the operation code. ")
    for ((index, code) in operationCodes.withIndex()) {
        print("$index - ${code.title}")
        if (index == operationCodes.size - 1) {
            print(": ")
        } else {
            print(", ")
        }
    }
}

fun addNewItem(file: File) {
    val item = readln()
    file.appendText(item + "\n")
}

fun showAllItems(file: File) {
    val items = file.readText().trim().split("\n")
    for ((index, item) in items.withIndex()) {
        println("$index - $item")
    }
}

fun deleteAllItems(file: File) {
    file.writeText("")
}