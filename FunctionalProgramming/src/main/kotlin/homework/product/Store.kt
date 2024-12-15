package homework.product

import extensions.filter
import extensions.myAlso

fun main() {
    ProductCardsRepository.productCards
        .myAlso {
            println("Filter by category CLOTHING")
        }.filter { it.category == ProductCategory.CLOTHING }.myAlso {
            println("Increase price")
        }.map { it.copy(price = it.price * 2) }.myAlso {
            println("Convert to strings")
        }.map { "${it.id} - ${it.name} - ${it.price}" }.myAlso {
            println("Print info")
        }.forEach(::println)

}