package corporation

class Director(
    id: Int,
    name: String,
    age: Int
) : Worker(
    id,
    name,
    age,
    PositionType.DIRECTOR
) {
    override fun work() {
        println("I'm drinking coffee and reading reports")
    }
}