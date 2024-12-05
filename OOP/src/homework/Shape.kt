package homework

import kotlin.math.round

abstract class Shape {

    val name: String = ""

    abstract fun area(): Double
    abstract fun perimeter(): Double

    protected fun roundToTwoDecimals(value: Double): Double{
        return round(value * 100) / 100
    }
}

