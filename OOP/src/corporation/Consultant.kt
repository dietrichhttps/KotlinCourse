package corporation

 class Consultant(
    name: String,
    age: Int
): Worker(
    name,
    age
) {
    override fun work() {
        println("I'm consulting clients")
    }
}