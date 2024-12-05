package homework

class Circle(val radius: Double): Shape() {

    override fun area(): Double {
        return roundToTwoDecimals(Math.PI * (radius * radius))
    }

    override fun perimeter(): Double {
        return roundToTwoDecimals(2 * Math.PI * radius)
    }

}