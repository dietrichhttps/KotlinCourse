package monster

import kotlin.random.Random

class Monster {
    val pawsCount: Int
    val eyesCount: Int
    val fangsCount: Int
    val clawsCount: Int
    val tentaclesCount: Int

    companion object {
        fun getRandomInt(): Int {
            return Random.nextInt(1, 10)
        }
    }

    constructor(pawsCount: Int, eyesCount: Int, fangsCount: Int, clawsCount: Int, tentaclesCount: Int) {
        this.pawsCount = pawsCount
        this.eyesCount = eyesCount
        this.fangsCount = fangsCount
        this.clawsCount = clawsCount
        this.tentaclesCount = tentaclesCount
    }

    constructor(num: Int) : this(num, num, num, num, num)

    constructor() : this(
        getRandomInt(), getRandomInt(), getRandomInt(), getRandomInt(), getRandomInt()
    )

    fun printInfo() {
        val outputString = "Кол-во лап: $pawsCount\n" +
                "Кол-во глаз: $eyesCount\n" +
                "Кол-во клыков: $fangsCount\n" +
                "Кол-во когтей: $clawsCount\n" +
                "Кол-во щупалец: $tentaclesCount"
        print(outputString)
    }


}