package draft

data class Order(
    val id: Int,
    val status: String,
    val type: String
)

fun removeCompletedOrders(orders: List<Order>, typeToRemove: String): List<Order> {
    val mutableOrders = orders.toMutableList()
    mutableOrders.removeIf { it.status == "completed" && it.type == typeToRemove }
    return mutableOrders
}