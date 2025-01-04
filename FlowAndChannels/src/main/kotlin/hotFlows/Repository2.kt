package hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.plus
import java.util.concurrent.Executors

object Repository2 {

    private val scope = CoroutineScope(Executors.newCachedThreadPool().asCoroutineDispatcher())

    val timer = getTimerFlow().shareIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed()
    )

    private fun getTimerFlow(): Flow<Int> {
        return flow {
            var seconds = 0
            while (true) {
                println("Emitted: $seconds")
                emit(seconds++)
                delay(1000)
            }
        }
    }
}