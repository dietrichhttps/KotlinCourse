package corporation

class Consultant(
    id: Int,
    name: String,
    age: Int
) : Worker(
    id,
    name,
    age,
    PositionType.CONSULTANT
) {
    override fun work() {
        println("I'm consulting clients")
    }
}