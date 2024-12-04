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

    val items = mutableListOf<ProductCard>()
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
        file.appendText("$productName%")
        print("Enter the product brand: ")
        val productBrand = readln()
        file.appendText("$productBrand%")
        print("Enter the product price: ")
        val productPrice = readln().toInt()
        file.appendText("$productPrice%")
        when (productType) {
            ProductType.FOOD -> {
                print("Enter the calorie: ")
                val calorie = readln().toFloat()
                file.appendText("$calorie%")
            }

            ProductType.ELECTRONIC -> {
                print("Enter the voltage: ")
                val voltage = readln().toInt()
                file.appendText("$voltage%")
            }

            ProductType.SHOE -> {
                print("Enter the size: ")
                val size = readln().toFloat()
                file.appendText("$size%")
            }
        }
        file.appendText("$productType\n")
    }

    fun showAllItems() {
        val lines = file.readLines()
        for (line in lines) {
            val args = line.split("%")
            val name = args[0]
            val brand = args[1]
            val price = args[2].toInt()
            val type = args.last()
            val productType = ProductType.valueOf(type)
            val item = when (productType) {
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
            items.add(item)
            item.printInfo()
        }
    }

    fun removeProductCard() {

    }
}