package collections

class Item (
    val value: Int
) {
     override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        return value == other.value
    }

    override fun hashCode(): Int {
        return value
    }

    override fun toString(): String {
        return "Item(value=$value)"
    }


}