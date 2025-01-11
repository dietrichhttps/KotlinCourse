package homework.phonebook

fun main() {
    val phonebook = mutableMapOf(
        "Piero" to 8999222333,
        "Silvestre" to 8666775555
    )
    print("Enter new contact: \nName: ")
    val name = readln()
    print("Phone number: ")
    val phoneNumber = readln().toLong()
    phonebook[name] = phoneNumber
    showPhoneNumber(phonebook)
}

fun showPhoneNumber(phonebook: Map<String, Long>) {
    while (true) {
        print("Enter query name or 0 to exit: ")
        val input = readln()
        if (input == "0") break
        println(phonebook[input]?: "Name not found...")
    }
}