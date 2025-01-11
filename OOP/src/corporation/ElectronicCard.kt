package corporation

data class ElectronicCard(
    override val name: String,
    override val brand: String,
    override val price: Int,
    val voltage: Int
): ProductCard(
    name = name,
    brand = brand,
    price = price,
    ProductType.ELECTRONIC
)