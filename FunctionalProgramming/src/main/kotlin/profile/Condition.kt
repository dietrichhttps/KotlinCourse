package profile

interface Condition {

    fun isSuitable(person: Person): Boolean
}