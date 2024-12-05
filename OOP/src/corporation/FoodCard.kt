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

    override fun toString(): String {
        return "Name $name Brand: $brand Price: $price Calories: $calorie Product type: $productType"
    }
}