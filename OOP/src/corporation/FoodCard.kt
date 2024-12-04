package corporation

class FoodCard(
    name: String,
    brand: String,
    price: Int,
    val calorie: Float
): ProductCard(
    name = name,
    brand = brand,
    price = price,
    ProductType.FOOD
) {
    override fun printInfo() {
        super.printInfo()
        println("Calories: $calorie")
    }
}