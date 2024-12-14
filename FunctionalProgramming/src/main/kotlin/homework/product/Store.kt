package homework.product

import extensions.filter

fun main() {
    ProductCardsRepository.productCards
        .filter { it.category == ProductCategory.CLOTHING }
        .map { it.copy(price = it.price * 2) }
        .map { "${it.id} - ${it.name} - ${it.price}" }
        .forEach { println(it)}
}