package homework.shapes

class Rectangle(val width: Double, val height: Double) : Shape() {

    override fun area(): Double {
        return roundToTwoDecimals(width * height)
    }

    override fun perimeter(): Double {
        return roundToTwoDecimals(2 * (width + height))
    }
}