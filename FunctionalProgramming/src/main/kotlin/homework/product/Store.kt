package homework.product

fun main() {
    val productCards = ProductCardsRepository.productCards
    val filteredProductCards = filter(productCards) { it.category == ProductCategory.CLOTHING }
    val doubledPrice = transform(filteredProductCards) { it.copy(price = it.price * 2) }
    val transformedToString = transform(doubledPrice) { "${it.id} - ${it.name} - ${it.price}" }
    transformedToString.forEach { println(it) }

}

fun <R> transform(productCards: List<ProductCard>, operation: (ProductCard) -> R): List<R> {
    val result = mutableListOf<R>()
    for (productCard in productCards) {
        result.add(operation(productCard))
    }
    return result
}

fun filter(productCards: List<ProductCard>, isSuitable: (ProductCard) -> Boolean): List<ProductCard> {
    val filteredProductCards = mutableListOf<ProductCard>()
    for (productCard in productCards) {
        if (isSuitable(productCard)) {
            filteredProductCards.add(productCard)
        }
    }
    return filteredProductCards
}