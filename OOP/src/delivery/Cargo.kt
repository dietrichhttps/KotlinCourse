class Cargo(
    val length: Int,
    val width: Int,
    val height: Int,
    val typePackaging: String,
    val netWeight: Double,
    val grossWeight: Double
) {
    fun printInfo() {
        val outputString = "Длина: $length \nШирина: $width \nВысота: $height \nТип упаковки: $typePackaging \nВес нетто: $netWeight\nВес брутто: $grossWeight"
        print(outputString)
    }
}