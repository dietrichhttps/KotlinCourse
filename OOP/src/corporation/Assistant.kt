package corporation

 class Assistant(
    name: String,
    age: Int
): Worker(
    name,
    age
) {
    override fun work() {
        println("I'm answering the phone now")
    }
}