package cancellation

import kotlinx.coroutines.*
import kotlinx.coroutines.CancellationException
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    val job = scope.launch {
        timer()
    }
    Thread.sleep(3000)
    job.cancel()
}

private suspend fun timer() {
    withContext(dispatcher) {
        var seconds = 0
        while (true) {
            try {
                ensureActive()
                println("Hello")
                println(seconds++)
                Thread.sleep(1000)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {

            }
        }
    }
}