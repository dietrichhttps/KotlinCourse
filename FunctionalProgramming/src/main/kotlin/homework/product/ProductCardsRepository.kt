package homework.product

import kotlinx.serialization.json.Json
import java.io.File

object ProductCardsRepository {

    private val file = File("product_cards.json")

    private val _productCards = loadAllProductCards()
    val productCards
        get() = _productCards.toList()

    private fun loadAllProductCards(): List<ProductCard> {
        val content = file.readText().trim()
        return Json.decodeFromString(content)
    }
}