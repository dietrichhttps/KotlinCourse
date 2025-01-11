package exceptions

fun main() {
//    try {
//        val a = readln().toInt()
//        val b = readln().toInt()
//        println(a / b)
//    } catch (exception: ArithmeticException) {
//        println("You can't divide by zero")
//    } catch (exception: NumberFormatException) {
//        println("Wrong input")
//    } catch (exception: Throwable) {
//        println("Common exception")
//    }

    try {
        val list = listOf(1, 2, 3, 4)
        val inputIndex = readln().toInt()
        println(list[inputIndex])
    } catch (exception: IndexOutOfBoundsException) {
        println("Wrong index")
    }

    println("After try-catch")
}