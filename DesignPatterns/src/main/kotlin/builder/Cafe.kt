package builder

fun main() {
    val drink = Drink.Builder()
        .type("Tea")
        .temperature("Cold")
        .diningOption("In cafe")
        .build()
    println(drink)
}