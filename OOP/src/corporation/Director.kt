package corporation

 class Director(
    name: String,
    age: Int
): Worker(
    name,
    age
) {
    override fun work() {
        println("I'm drinking coffee and reading reports")
    }
}