package corporation

import java.io.File

object WorkersRepository {

    private val fileWorkers = File("employees.txt ")
    private val _workers = loadAllEmployees()
    val workers: List<Worker>
        get() = _workers.toList()

    fun findAssistant(): Assistant? {
        for (worker in workers) {
            if (worker is Assistant) {
                return worker
            }
        }
        return null
    }

    fun findDirector(): Director? {
        for (worker in workers) {
            if (worker is Director) {
                return worker
            }
        }
        return null
    }

    fun registerNewEmployee(newWorker: Worker) {
        _workers.add(newWorker)
    }

    fun saveChanges() {
        val content = StringBuilder()
        for (worker in _workers) {
            content.append("${worker.id}%${worker.name}%${worker.age}%${worker.salary}%${worker.position}\n")
        }
        fileWorkers.writeText(content.toString())
    }

    private fun loadAllEmployees(): MutableSet<Worker> {
        val employees = mutableSetOf<Worker>()

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
        for (worker in _workers) {
            if (worker.id == id) {
                _workers.remove(worker)
                break
            }
        }
    }

    fun changeSalary(id: Int, salary: Int) {
        for (worker in _workers) {
            if (worker.id == id) {
                val newWorker = worker.copy(salary = salary)
                _workers.remove(worker)
                _workers.add(newWorker)
                break
            }
        }
    }

    fun changeAge(id: Int, age: Int) {
        for (worker in _workers) {
            if (worker.id == id) {
                val newWorker = worker.copy(age = age)
                _workers.remove(worker)
                _workers.add(newWorker)
                break
            }
        }
    }
}