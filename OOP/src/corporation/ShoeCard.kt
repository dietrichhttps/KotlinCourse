package corporation

class ShoeCard(
    name: String,
    brand: String,
    price: Int,
    val size: Float
): ProductCard(
    name = name,
    brand = brand,
    price = price
) {
    override fun printInfo() {
        super.printInfo()
        print("Size: $size")
    }
}