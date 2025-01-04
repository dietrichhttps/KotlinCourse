package hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val scope = CoroutineScope(Executors.newCachedThreadPool().asCoroutineDispatcher())

fun main() {
    val flow = Repository2.timer
    scope.launch {
        delay(3000)
        flow.take(1).collect {
            println("Coroutine 1: $it")
        }
    }
    scope.launch {
        delay(5000)
        flow.collect {
            println("Coroutine 2: $it")
        }
    }
}