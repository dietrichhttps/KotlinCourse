package users

fun task(listUser: List<String>) {
    val mutableListUser = listUser.toMutableList()
    val operationCode = readln()
    when (operationCode) {
        "SHOW"  -> showAllUsers(mutableListUser)
        "ADD" -> addUser(mutableListUser)
        "REMOVE" -> removeUser(mutableListUser)
        "REMOVE_AT" -> removeAt(mutableListUser)
        else -> print("Некорректное значение")
    }
}

fun removeAt(mutableListUser: MutableList<String>) {
    mutableListUser.removeAt(readln().toInt())
    showAllUsers(mutableListUser)
}

fun removeUser(mutableListUser: MutableList<String>) {
    val userToRemove = readln()
    if (mutableListUser.contains(userToRemove)) {
        mutableListUser.remove(userToRemove)
    }
    showAllUsers(mutableListUser)
}

fun addUser(mutableListUser: MutableList<String>) {
    mutableListUser.add(readln())
    showAllUsers(mutableListUser)
}

fun showAllUsers(mutableListUser: MutableList<String>) {
    for (user in mutableListUser) {
        println(user)
    }
}

