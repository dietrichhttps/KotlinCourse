package homework.product

import extensions.filter
import extensions.myForEach

fun main() {
    ProductCardsRepository.productCards
        .filter { it.category == ProductCategory.CLOTHING }
        .map { it.copy(price = it.price * 2) }
        .map { "${it.id} - ${it.name} - ${it.price}" }
        .myForEach { println(it) }
}