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
        super.printInfo()
        println("Voltage: $voltage")
    }
}