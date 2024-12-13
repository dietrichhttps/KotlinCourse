package homework.product

fun main() {
    val productCards = ProductCardsRepository.productCards
    var filteredProductCards = filter(productCards) { it.price > 500 }
    filteredProductCards = filter(filteredProductCards) { it.rating > 4 }
    filteredProductCards = filter(filteredProductCards) { it.category == ProductCategory.SPORTS }
    filteredProductCards.forEach { println(it) }
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