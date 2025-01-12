package delegates

fun main() {
//    val zombie = Zombie("Max")
//    val human = Zombie("Jack")
//    zombie.move()
//    zombie.fight()
//    human.move()
//    human.fight()
//
//    val premiumHuman = PremiumPlayer(human)
//    premiumHuman.callForHelp()
//    premiumHuman.move()
//    val flyingHuman = FlyingPlayer(human)
//    flyingHuman.fly()

    val loggingMutableList = LoggingMutableList(arrayListOf<Int>())
    loggingMutableList.add(10)
}

interface Player {

    fun move()

    fun fight()
}

class Zombie(val userName: String) : Player {
    override fun move() {
        println("I'm walking very slowly...")
    }

    override fun fight() {
        println("I'm eating people...")
    }
}

class Human(val userName: String) : Player {
    override fun move() {
        println("I'm running very fast...")
    }

    override fun fight() {
        println("I'm shooting a gun...")
    }
}

data class FlyingPlayer(val player: Player) : Player by player {

    fun fly() {
        println("I'm flying!")
    }
}

data class PremiumPlayer(val player: Player) : Player by player {

    override fun move() {
        println("I'm moving like premium player...")
    }

    fun callForHelp() {
        println("HELP ME!!!")
    }
}

data class LoggingMutableList<T>(private val mutableList: MutableList<T>) : MutableList<T> by mutableList {
    override fun add(element: T): Boolean {
        return mutableList.add(element).also {
            println("Element $element was added")
        }
    }
}