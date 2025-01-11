package price

class Drill(
    brand: String,
    model: String,
    power: Int,
    weight: Double,
    cableLength: Int,
    price: Price,
    voltage: Int,
    val drillChuckDiameter: Int,
    val minRotationSpeed: Int,
    val maxRotationSpeed: Int
): PowerTool(
    brand = brand,
    model = model,
    power = power,
    weight = weight,
    cableLength = cableLength,
    price = price,
    voltage = voltage,
) {
    override fun turnOn() {
        println("Дрель сверлит")
    }
}