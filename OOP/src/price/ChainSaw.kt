package price

class ChainSaw(
    brand: String,
    model: String,
    power: Int,
    weight: Double,
    cableLength: Int,
    price: Price,
    voltage: Int,
    val chainSawTireLength: Int,
    val chainLinksCount: Int,
    val chainStep: Double,
) : PowerTool(
    brand = brand,
    model = model,
    power = power,
    weight = weight,
    cableLength = cableLength,
    price = price,
    voltage = voltage,
) {
    override fun turnOn() {
        println("Цепная пила пилит")
    }
}