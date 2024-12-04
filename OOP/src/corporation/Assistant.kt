package corporation

class Assistant(
    id: Int,
    name: String,
    age: Int
) : Worker(
    id,
    name,
    age,
    PositionType.ASSISTANT
) {
    override fun work() {
        println("I'm answering the phone now")
    }
}