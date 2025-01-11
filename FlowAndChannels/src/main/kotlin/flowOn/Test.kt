package flowOn

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        getFlow().onStart { println("onStart: ${getCurrentThread()}") }
            .onEach { println("onEach: ${getCurrentThread()}") }
            .flowOn(dispatcher)
            .map { println("map: ${getCurrentThread()}") }
            .flowOn(Dispatchers.Default)
            .collect { println("collect: ${getCurrentThread()}") }
    }
}

private fun getFlow() = flow {
    var seconds = 0
    while (true) {
        println("Emitted: $seconds in ${getCurrentThread()}")
        emit(seconds++)
        delay(1000)
    }
}

private fun getCurrentThread(): String = Thread.currentThread().name