package corporation

fun main() {
    val accountant = Accountant(100,"Mike", 60)
    val employees = accountant.loadAllEmployees()
    for (employee in employees) {
        if (employee is Cleaner) {
            employee.clean()
        }
        if (employee is Supplier) {
            employee.supply()
        }
    }
}