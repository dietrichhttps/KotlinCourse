package car

class Car {
    val brand: String
    val model: String
    val enginePower: Int
    val bodyColor: String

    constructor(brand: String, model: String, enginePower: Int, bodyColor: String) {
        this.brand = brand
        this.model = model
        this.enginePower = enginePower
        this.bodyColor = bodyColor
    }

    fun drive() {
        val string1: String
        val string2: String
        if (enginePower >= 120) {
            string1 = "быстро"
            string2 = "недалеко"
        } else {
            string1 = "далеко"
            string2 = "небыстро"
        }
        print("Еду $string1, но $string2 на $brand $model")
    }

    fun refuel(brand: String, format: String, amount: Int) {
        val outputString = "Произведена заправка на АЗС \"$brand\"\nМарка бензина: $format\nКол-во литров: $amount"
        print(outputString)
    }

    fun printInfo() {
        print("$brand $model $enginePower л.с. $bodyColor")
    }
}

fun task() {
    //создайте здесь экземпляр Car и вызовите у него метод refuel()
//    val car = Car()
//    val params = readln().split(" ")
//    car.refuel(params[0], params[1], params[2].toInt())
}