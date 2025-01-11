package homework.task_manager

// Task.kt
class Task(
    val id: Int,
    val title: String,
    val description: String,
    val assignedTo: String,
    val status: String,
    val priority: String
) {

    fun copy(
        title: String = this.title,
        description: String = this.description,
        assignedTo: String = this.assignedTo,
        status: String = this.status,
        priority: String = this.priority
    ) : Task {
        return Task(this.id,
            title = title,
            description = description,
            assignedTo = assignedTo,
            status = status,
            priority = priority
        )
    }

    fun printTaskInfo() {
        println("Задача #$id: $title")
        println("Описание: $description")
        println("Назначена: $assignedTo")
        println("Статус: $status")
        println("Приоритет: $priority")
        println()
    }
}

// Employee.kt
class Employee(val id: Int, val name: String) {

    private val _tasks = mutableListOf<Task>()
    val tasks: List<Task>
        get() = _tasks.toList()

    private val _archivedTasks = mutableListOf<Task>() // TODO: Реализовать функциональность архивирования задач
    val archivedTasks: List<Task>
        get() = _archivedTasks.toList()

    fun addTask(task: Task) {
        _tasks.add(task)
        println("Добавлена задача: ${task.title} для сотрудника $name.")
    }

    fun removeTask(taskId: Int) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            _tasks.remove(task)
            println("Задача ${task.title} удалена.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun updateTaskStatus(taskId: Int, newStatus: String) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            val newTask = task.copy(status = newStatus)
            _tasks[_tasks.indexOf(task)] = newTask
            _archivedTasks.add(task)
            println("Статус задачи ${task.title} изменен на '$newStatus'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun changeTaskAssignee(taskId: Int, newAssignee: String) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            val newTask = task.copy(assignedTo = newAssignee)
            _tasks[_tasks.indexOf(task)] = newTask
            _archivedTasks.add(task)
            println("Задача ${task.title} переназначена на $newAssignee.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun updateTaskPriority(taskId: Int, newPriority: String) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            val newTask = task.copy(priority = newPriority)
            _tasks[_tasks.indexOf(task)] = newTask
            _archivedTasks.add(task)
            println("Приоритет задачи ${task.title} изменен на '$newPriority'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun modifyTaskDetails(taskId: Int, newTitle: String, newDescription: String) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            val newTask = task.copy(title = newTitle, description = newDescription)
            _tasks[_tasks.indexOf(task)] = newTask
            _archivedTasks.add(task)
            println("Детали задачи ${task.id} обновлены.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun printTasks() {
        println("Список задач для сотрудника $name:")
        _tasks.forEach { it.printTaskInfo() }
    }
}

// Project.kt
class Project(val name: String) {
    private val employees = mutableListOf<Employee>()

    fun addEmployee(employee: Employee) {
        employees.add(employee)
        println("Сотрудник ${employee.name} добавлен в проект '$name'.")
    }

    fun assignTaskToEmployee(employeeId: Int, task: Task) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.addTask(task)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun updateEmployeeTaskStatus(employeeId: Int, taskId: Int, newStatus: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.updateTaskStatus(taskId, newStatus)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun changeEmployeeTaskAssignee(employeeId: Int, taskId: Int, newAssignee: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.changeTaskAssignee(taskId, newAssignee)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun updateEmployeeTaskPriority(employeeId: Int, taskId: Int, newPriority: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.updateTaskPriority(taskId, newPriority)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun modifyEmployeeTaskDetails(employeeId: Int, taskId: Int, newTitle: String, newDescription: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.modifyTaskDetails(taskId, newTitle, newDescription)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun printAllTasks() {
        println("Все задачи проекта '$name':")
        employees.forEach { it.printTasks() }
    }
}