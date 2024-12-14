package homework.product

fun main() {
    val productCards = ProductCardsRepository.productCards
        .filter { it.category == ProductCategory.CLOTHING }
        .transform { it.copy(price = it.price * 2) }
        .transform { "${it.id} - ${it.name} - ${it.price}" }
        .forEach { println(it)}
}

fun <R> List<ProductCard>.transform(operation: (ProductCard) -> R): List<R> {
    val result = mutableListOf<R>()
    for (productCard in this) {
        result.add(operation(productCard))
    }
    return result
}

fun List<ProductCard>.filter(isSuitable: (ProductCard) -> Boolean): List<ProductCard> {
    val filteredProductCards = mutableListOf<ProductCard>()
    for (productCard in this) {
        if (isSuitable(productCard)) {
            filteredProductCards.add(productCard)
        }
    }
    return filteredProductCards
}