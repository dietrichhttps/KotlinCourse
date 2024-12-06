package corporation

import java.io.File

class ProductCardsRepository {

    private val fileProductCards = File("product_cards.txt ")
    val productCards = loadAllCards()

    fun registerNewItem(productCard: ProductCard) {
        productCards.add(productCard)
    }

    fun saveChanges() {
        val content = StringBuilder()
        for (productCard in productCards) {
            content.append("${productCard.name}%${productCard.brand}%${productCard.price}%")
            when (productCard) {
                is FoodCard -> content.append("${productCard.calorie}%")
                is ElectronicCard -> content.append("${productCard.voltage}%")
                is ShoeCard -> content.append("${productCard.size}%")
            }
            content.append("${productCard.productType}\n")
        }
        fileProductCards.writeText(content.toString())
    }

    private fun loadAllCards(): MutableList<ProductCard> {
        val productCards = mutableListOf<ProductCard>()

        if (!fileProductCards.exists()) fileProductCards.createNewFile()
        val lines = fileProductCards.readLines()
        if (lines.isEmpty()) return productCards

        for (line in lines) {
            val args = line.split("%")
            val name = args[0]
            val brand = args[1]
            val price = args[2].toInt()
            val type = args.last()
            val productType = ProductType.valueOf(type)
            val productCard = when (productType) {
                ProductType.FOOD -> {
                    val calorie = args[3].toFloat()
                    FoodCard(name, brand, price, calorie)
                }

                ProductType.ELECTRONIC -> {
                    val voltage = args[3].toInt()
                    ElectronicCard(name, brand, price, voltage)
                }

                ProductType.SHOE -> {
                    val size = args[3].toFloat()
                    ShoeCard(name, brand, price, size)
                }
            }
            productCards.add(productCard)
        }
        return productCards
    }

    fun removeProductCard(name: String) {
        for (productCard in productCards) {
            if (productCard.name == name) {
                productCards.remove(productCard)
                break
            }
        }
    }
}