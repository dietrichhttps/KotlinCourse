package corporation

class ElectronicCard(
    name: String,
    brand: String,
    price: Int,
    val voltage: Int
): ProductCard(
    name = name,
    brand = brand,
    price = price,
    ProductType.ELECTRONIC
) {
    override fun printInfo() {
        println("Name $name Brand: $brand Price: $price Voltage: $voltage Product type: $productType")
    }
}