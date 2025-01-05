package synchroization

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.Executors

class Counter {

    private val mutex = Mutex()
    var value = 0

    suspend fun inc() {
        mutex.withLock {
            delay(1)
            value++
        }
    }
}

private val scope = CoroutineScope(Executors.newCachedThreadPool().asCoroutineDispatcher())

fun main() {
    val counter = Counter()
    scope.launch {
        buildList {
            repeat(100) {
                scope.launch {
                    repeat(10) {
                        counter.inc()
                    }
                }.let { add(it) }
            }
        }.joinAll()
        println(counter.value)
    }
}