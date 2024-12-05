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
        println("Name $name Brand: $brand Price: $price Calories: $calorie Product type: $productType")
    }
}