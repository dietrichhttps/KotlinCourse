package corporation

import corporation.OperationCode.*

class Accountant(
    id: Int,
    name: String,
    age: Int,
    salary: Int
) : Worker(
    id = id,
    name = name,
    age = age,
    salary = salary,
    position = Position.ACCOUNTANT
), Cleaner, Supplier {

    val workersRepository = WorkersRepository()
    val productCardsRepository = ProductCardsRepository()


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
                FIRE_AN_EMPLOYEE -> fireEmployee()
                SHOW_ALL_EMPLOYEE -> showAllEmployees()
                CHANGE_SALARY -> changeSalary()
            }
        }
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
        val productCard = when (productType) {
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
        productCardsRepository.registerNewItem(productCard)
    }

    private fun showAllItems() {
        val cards = productCardsRepository.loadAllCards()
        if (cards.isNotEmpty()) {
            for (card in cards) {
                card.printInfo()
            }
        }
    }

    private fun removeProductCard() {
        print("Enter name of card for removing: ")
        val name = readln()
        productCardsRepository.removeProductCard(name)
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
        val positionIndex = readln().toInt()
        val position = positions[positionIndex]
        print("Enter id: ")
        val id = readln().toInt()
        print("Enter name: ")
        val name = readln()
        print("Enter age: ")
        val age = readln().toInt()
        print("Enter salary: ")
        val salary = readln().toInt()
        val employee = when (position) {
            Position.DIRECTOR -> Director(id, name, age, salary)
            Position.ACCOUNTANT -> Accountant(id, name, age, salary)
            Position.ASSISTANT -> Assistant(id, name, age, salary)
            Position.CONSULTANT -> Consultant(id, name, age, salary)
        }
        workersRepository.registerNewEmployee(employee)
    }

    private fun showAllEmployees() {
        val employees = workersRepository.loadAllEmployees()
        for (employee in employees) {
            employee.printInfo()
        }
    }


    private fun fireEmployee() {
        print("Enter employee's id to fire: ")
        val id = readln().toInt()
        workersRepository.fireEmployee(id)
    }

    private fun changeSalary() {
        print("Enter employee's id to change salary: ")
        val id = readln().toInt()
        print("Enter new salary: ")
        val salary = readln().toInt()
        workersRepository.changeSalary(id, salary)
    }
}