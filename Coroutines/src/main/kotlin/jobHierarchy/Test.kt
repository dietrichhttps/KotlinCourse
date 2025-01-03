package jobHierarchy

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val parent = Job()
private val dispatcherIO = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcherIO + parent)

fun main() {
    scope.launch {
        coroutineContext.job.parent?.let { println(it)}
        printNumber(1)
    }

    scope.launch {
        coroutineContext.job.parent?.let { println(it)}
        printNumber(2)
    }

    Thread.sleep(3000)
    parent.cancel()
}

private suspend fun printNumber(number: Int) {
    while (true) {
        println(number)
        delay(1000)
    }
}