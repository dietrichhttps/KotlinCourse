package corporation

import java.io.File

class WorkersRepository {

    private val fileEmployees = File("employees.txt ")

    fun registerNewEmployee(worker: Worker) {
        saveEmployeeToFile(worker)
    }

    fun changeSalary(id: Int, salary: Int) {
        val employees = loadAllEmployees()
        fileEmployees.writeText("")
        for (employee in employees) {
            if (employee.id == id) {
                employee.setSalary(salary)
            }
            saveEmployeeToFile(employee)
        }
    }

    private fun saveEmployeeToFile(worker: Worker) {
        fileEmployees.appendText("${worker.id}%${worker.name}%${worker.age}%${worker.getSalary()}%${worker.position}\n")
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
            val salary = args[3].toInt()
            val type = args.last()
            val position = Position.valueOf(type)
            val employee = when (position) {
                Position.DIRECTOR -> Director(id, name, age, salary)
                Position.ACCOUNTANT -> Accountant(id, name, age, salary)
                Position.ASSISTANT -> Assistant(id, name, age, salary)
                Position.CONSULTANT -> Consultant(id, name, age, salary)
            }
            employees.add(employee)
        }
        return employees
    }

    fun fireEmployee(id: Int) {
        val employees = loadAllEmployees()
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