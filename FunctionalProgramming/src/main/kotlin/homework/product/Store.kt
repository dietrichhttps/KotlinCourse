package homework.product

fun main() {
    val productCards = ProductCardsRepository.productCards
    productCards.listIterator().forEach { println(it) }
}