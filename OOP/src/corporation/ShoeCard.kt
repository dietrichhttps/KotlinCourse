package corporation

class ShoeCard(
    name: String,
    brand: String,
    price: Int,
    val size: Float
): ProductCard(
    name = name,
    brand = brand,
    price = price,
    ProductType.SHOE
) {
    override fun printInfo() {
        println("Name $name Brand: $brand Price: $price Size: $size Product type: $productType")
    }
}