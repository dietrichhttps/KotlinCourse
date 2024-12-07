package corporation

import java.io.File

object WorkersRepository {

    private val fileWorkers = File("employees.txt ")
    val workers = loadAllEmployees()

    fun registerNewEmployee(worker: Worker) {
        workers.add(worker)
    }

    fun saveChanges() {
        val content = StringBuilder()
        for (worker in workers) {
            content.append("${worker.id}%${worker.name}%${worker.age}%${worker.getSalary()}%${worker.position}\n")
        }
        fileWorkers.writeText(content.toString())
    }

    private fun loadAllEmployees(): MutableList<Worker> {
        val employees = mutableListOf<Worker>()

        if (!fileWorkers.exists()) fileWorkers.createNewFile()
        val lines = fileWorkers.readLines()
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
        for (worker in workers) {
            if (worker.id == id) {
                workers.remove(worker)
                break
            }
        }
    }

    fun changeSalary(id: Int, salary: Int) {
        for (worker in workers) {
            if (worker.id == id) {
                worker.setSalary(salary)
            }
        }
    }
}