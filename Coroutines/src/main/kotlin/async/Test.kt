package async

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    println("Exception caught")
}
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(
    dispatcher + CoroutineName("My coroutine") + Job() + exceptionHandler
)

fun main() {
//    val deferred = scope.async {
//        method()
//    }

    scope.launch {
        async {
            method()
        }
    }

    scope.launch {
        method2()
    }
}

private suspend fun method() {
    delay(3000)
    error("")
}

private suspend fun method2() {
    delay(5000)
    println("Method2 is finished")
}