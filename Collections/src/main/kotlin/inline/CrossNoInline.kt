package inline

import kotlin.concurrent.thread

fun main() {
    executeCommand(
        command1 = { println("Command1") },
        command2 = { println("Command1") })
}

private inline fun executeAsync(crossinline command: () -> Unit) {
    thread { command() }
}

private inline fun executeCommand(command1: () -> Unit, noinline command2: () -> Unit) {
    command1()
    Invoker.addCommand(command2)
}

object Invoker {

    private val commands = mutableListOf<() -> Unit>()

    fun addCommand(command: () -> Unit) {
        commands.add(command)
    }
}