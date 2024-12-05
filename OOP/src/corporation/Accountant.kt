package corporation

import corporation.OperationCode.*
import java.io.File

class Accountant(id: Int, name: String, age: Int) : Worker(id, name, age, Position.ACCOUNTANT), Cleaner, Supplier {

    private val fileProductCards = File("product_cards.txt ")
    private val fileEmployees = File("employees.txt ")

    override fun clean() {
        println("I'm ${position.title}. I'm cleaning workplace...")
    }

    override fun supply() {
        println("I'm ${position.title}. I'm buying things...")
    }

    override fun work() {
        val operationCodes = OperationCode.entries
        while (true) {
            println("Enter the operation code.")
            for ((index, code) in operationCodes.withIndex()) {
                println("$index - ${code.title}")
            }
            val operationCodeIndex = readln().toInt()
            val operationCode: OperationCode = operationCodes[operationCodeIndex]
            when (operationCode) {
                EXIT -> break
                REGISTER_NEW_ITEM -> registerItem()
                SHOW_ALL_ITEMS -> showAllItems()
                REMOVE_PRODUCT_CARD -> removeProductCard()
                REGISTER_NEW_EMPLOYEE -> registerNewEmployee()
                FIRE_AN_EMPLOYEE -> fireAnEmployee()
                SHOW_ALL_EMPLOYEE -> showAllEmployees()
            }
        }
    }

    private fun loadAllCards(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()

        if (!fileProductCards.exists()) fileEmployees.createNewFile()
        val lines = fileProductCards.readLines()
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

    private fun saveProductCardToFile(productCard: ProductCard) {
        fileProductCards.appendText("${productCard.name}%${productCard.brand}%${productCard.price}%")
        when (productCard) {
            is FoodCard -> fileProductCards.appendText("${productCard.calorie}%")
            is ElectronicCard -> fileProductCards.appendText("${productCard.voltage}%")
            is ShoeCard -> fileProductCards.appendText("${productCard.size}%")
        }
        fileProductCards.appendText("${productCard.productType}\n")
    }

    private fun registerItem() {
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

    private fun showAllItems() {
        val cards = loadAllCards()
        if (cards.isNotEmpty()) {
            for (card in cards) {
                card.printInfo()
            }
        }

    }

    private fun removeProductCard() {
        val cards = loadAllCards()
        print("Enter name of card for removing: ")
        val name = readln()
        for (card in cards) {
            if (card.name == name) {
                cards.remove(card)
                break
            }
        }
        fileProductCards.writeText("")
        for (card in cards) {
            saveProductCardToFile(card)
        }
    }

    private fun registerNewEmployee() {
        val positions = Position.entries
        print("Choose position - ")
        for ((index, type) in positions.withIndex()) {
            print("$index - ${type.title}")
            if (index == positions.size - 1) {
                print(": ")
            } else {
                print(", ")
            }
        }
        val positionTypeIndex = readln().toInt()
        val positionType = positions[positionTypeIndex]
        print("Enter id: ")
        val employeeId = readln().toInt()
        print("Enter name: ")
        val employeeName = readln()
        print("Enter age: ")
        val employeeAge = readln().toInt()
        val employee = when (positionType) {
            Position.DIRECTOR -> Director(employeeId, employeeName, employeeAge)
            Position.ACCOUNTANT -> Accountant(employeeId, employeeName, employeeAge)
            Position.ASSISTANT -> Assistant(employeeId, employeeName, employeeAge)
            Position.CONSULTANT -> Consultant(employeeId, employeeName, employeeAge)
        }
        saveEmployeeToFile(employee)
    }

    private fun saveEmployeeToFile(worker: Worker) {
        fileEmployees.appendText("${worker.id}%${worker.name}%${worker.age}%${worker.position}\n")
    }

    private fun showAllEmployees() {
        val employees = loadAllEmployees()
        for (employee in employees) {
            employee.printInfo()
        }
    }

    fun loadAllEmployees(): MutableList<Worker> {
        val employees = mutableListOf<Worker>()

        if (!fileEmployees.exists()) fileEmployees.createNewFile()
        val lines = fileEmployees.readLines()
        if (lines.isEmpty()) return employees

        for (line in lines) {
            val args = line.split("%")
            val id = args[0].toInt()
            val name = args[1]
            val age = args[2].toInt()
            val type = args.last()
            val position = Position.valueOf(type)
            val employee = when (position) {
                Position.DIRECTOR -> Director(id, name, age)
                Position.ACCOUNTANT -> Accountant(id, name, age)
                Position.ASSISTANT -> Assistant(id, name, age)
                Position.CONSULTANT -> Consultant(id, name, age)
            }
            employees.add(employee)
        }
        return employees
    }

    private fun fireAnEmployee() {
        val employees = loadAllEmployees()
        print("Enter employee's id to fire: ")
        val id = readln().toInt()
        for (employee in employees) {
            if (employee.id == id) {
                employees.remove(employee)
                break
            }
        }
        fileEmployees.writeText("")
        for (employee in employees) {
            saveEmployeeToFile(employee)
        }
    }
}