package corporation

import corporation.OperationCode.*

class Accountant(
    name: String,
    age: Int
) : Worker(
    name,
    age
) {

    val items = mutableListOf<ProductCard>()

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
        print("Enter the product brand: ")
        val productBrand = readln()
        print("Enter the product price: ")
        val productPrice = readln().toInt()
        val productCard = when (productType) {
            ProductType.FOOD -> {
                print("Enter the calorie: ")
                FoodCard(productName, productBrand, productPrice, readln().toFloat())
            }

            ProductType.ELECTRONIC -> {
                print("Enter the voltage: ")
                ElectronicCard(productName, productBrand, productPrice, readln().toInt())
            }

            ProductType.SHOE -> {
                print("Enter the size: ")
                ShoeCard(productName, productBrand, productPrice, readln().toFloat())
            }
        }
        items.add(productCard)
    }

    fun showAllItems() {
        for (item in items) {
            item.printInfo()
        }
    }
}