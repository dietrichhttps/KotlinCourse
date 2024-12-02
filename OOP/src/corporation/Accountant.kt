package corporation

 class Accountant(
    name: String,
    age: Int
): Worker(
    name,
    age
) {
    override fun work() {
        print("Enter the operation code. 0 - exit, 1 - register new item: ")
        var operationCode = readln()
        while (operationCode != "0") {
            print("Enter the product type. 0 - Food, 1 - Electronic, 2 - Shoe: ")
            val productTypeCode = readln()
            print("Enter the product name: ")
            val productName = readln()
            print("Enter the product brand: ")
            val productBrand = readln()
            print("Enter the product price: ")
            val productPrice = readln().toInt()
            val productCard = when (productTypeCode) {
                "0" -> {
                    print("Enter the calorie: ")
                    FoodCard(productName, productBrand, productPrice, readln().toFloat())
                }
                "1" -> {
                    print("Enter the voltage: ")
                    ElectronicCard(productName, productBrand, productPrice, readln().toInt())
                }
                "2" -> {
                    print("Enter the size: ")
                    ShoeCard(productName, productBrand, productPrice, readln().toFloat())
                }

                else -> {
                    println("Invalid code")
                    null
                }
            }
            productCard?.printInfo()
            print("\nEnter the operation code. 0 - exit, 1 - register new item: ")
            operationCode = readln()
        }
    }
}