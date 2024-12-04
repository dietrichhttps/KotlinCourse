package corporation

import corporation.OperationCode.*
import java.io.File

class Accountant(
    name: String,
    age: Int
) : Worker(
    name,
    age
) {

    val file = File("product_cards.txt ")

    override fun work() {
        val operationCodes = OperationCode.entries
        while (true) {
            print("Enter the operation code. ")
            for ((index, code) in operationCodes.withIndex()) {
                print("$index - ${code.title}")
                if (index == operationCodes.size - 1) {
                    print(": ")
                } else {
                    print(", ")
                }
            }
            val operationCodeIndex = readln().toInt()
            val operationCode: OperationCode = operationCodes[operationCodeIndex]
            when (operationCode) {
                EXIT -> break
                REGISTER_NEW_ITEM -> registerItem()
                SHOW_ALL_ITEMS -> showAllItems()
                REMOVE_PRODUCT_CARD -> removeProductCard()
            }
        }
    }

    fun loadAllCards(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()
        val lines = file.readLines()
        if (lines.isEmpty()) return cards

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
            cards.add(productCard)
        }
        return cards
    }

    fun saveProductCardToFile(productCard: ProductCard) {
        file.appendText("${productCard.name}%${productCard.brand}%${productCard.price}%")
        when (productCard) {
            is FoodCard -> file.appendText("${productCard.calorie}%")
            is ElectronicCard -> file.appendText("${productCard.voltage}%")
            is ShoeCard -> file.appendText("${productCard.size}%")
        }
        file.appendText("${productCard.productType}\n")
    }

    fun registerItem() {
        val productTypes = ProductType.entries
        print("Enter the product type. ")
        for ((index, type) in productTypes.withIndex()) {
            print("$index - ${type.title}")
            if (index == productTypes.size - 1) {
                print(": ")
            } else {
                print(", ")
            }
        }
        val productTypeIndex = readln().toInt()
        val productType: ProductType = productTypes[productTypeIndex]
        print("Enter the product name: ")
        val productName = readln()
        print("Enter the product brand: ")
        val productBrand = readln()
        print("Enter the product price: ")
        val productPrice = readln().toInt()
        val card = when (productType) {
            ProductType.FOOD -> {
                print("Enter the calorie: ")
                val calorie = readln().toFloat()
                FoodCard(productName, productBrand, productPrice, calorie)
            }

            ProductType.ELECTRONIC -> {
                print("Enter the voltage: ")
                val voltage = readln().toInt()
                ElectronicCard(productName, productBrand, productPrice, voltage)
            }

            ProductType.SHOE -> {
                print("Enter the size: ")
                val size = readln().toFloat()
                ShoeCard(productName, productBrand, productPrice, size)
            }
        }
        saveProductCardToFile(card)
    }

    fun showAllItems() {
        val cards = loadAllCards()
        if (cards.isNotEmpty()) {
            for (card in cards) {
                card.printInfo()
            }
        }

    }

    fun removeProductCard() {
        val cards = loadAllCards()
        print("Enter name of card for removing: ")
        val name = readln()
        for (card in cards) {
            if (card.name == name) {
                cards.remove(card)
                break
            }
        }
        file.writeText("")
        for (card in cards) {
            saveProductCardToFile(card)
        }
    }
}