package exceptions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val scope = CoroutineScope(Executors.newCachedThreadPool().asCoroutineDispatcher())

fun main() {
    val flow = getFlow()
    scope.launch {
        flow
            .retry(3)
            .catch { println("Exception caught")}
            .collect {
                println(it)
            }
    }
}

private fun getFlow(): Flow<Int> = flow {
    repeat(5) {
        if (it == 3) {
            error("Exception in flow")
        }
        emit(it)
    }
}