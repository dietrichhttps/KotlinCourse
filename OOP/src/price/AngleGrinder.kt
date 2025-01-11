package price

class AngleGrinder(
    brand: String,
    model: String,
    power: Int,
    weight: Double,
    cableLength: Int,
    price: Price,
    voltage: Int,
    val discDiameter: Int,
    val isSmoothStart: Boolean,
    val isDustProtection: Boolean
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
        println("УШМ режет")
    }
}