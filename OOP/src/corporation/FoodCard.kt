package corporation

class FoodCard(
    name: String,
    brand: String,
    price: Int,
    val calorie: Float
): ProductCard(
    name = name,
    brand = brand,
    price = price
) {
    override fun printInfo() {
        super.printInfo()
        print("Calories: $calorie")
    }
}