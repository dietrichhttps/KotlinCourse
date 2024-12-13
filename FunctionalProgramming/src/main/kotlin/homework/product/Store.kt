package homework.product

fun main() {
    val productCards = ProductCardsRepository.productCards
    var filteredProductCards = filter(productCards, object : Condition {
        override fun isSuitable(productCard: ProductCard): Boolean {
            return productCard.price > 500
        }
    })
    filteredProductCards = filter(filteredProductCards, object: Condition {
        override fun isSuitable(productCard: ProductCard): Boolean {
            return productCard.rating > 4
        }
    })
    filteredProductCards = filter(filteredProductCards, object: Condition {
        override fun isSuitable(productCard: ProductCard): Boolean {
            return productCard.category == ProductCategory.SPORTS
        }
    })
    for (productCard in filteredProductCards) {
        println(productCard)
    }
}

fun filter(productCards: List<ProductCard>, condition: Condition): List<ProductCard> {
    val filteredProductCards = mutableListOf<ProductCard>()
    for (productCard in productCards) {
        if (condition.isSuitable(productCard)) {
            filteredProductCards.add(productCard)
        }
    }
    return filteredProductCards
}