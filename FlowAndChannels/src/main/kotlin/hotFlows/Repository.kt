package hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

object Repository {

    private val scope = CoroutineScope(Executors.newCachedThreadPool().asCoroutineDispatcher())

    private val _timer = MutableSharedFlow<Int>()
    val timer = _timer.asSharedFlow()

    init {
        scope.launch {
            var seconds = 0
            while (true) {
                println("Emitted: $seconds")
                _timer.emit(seconds++)
                delay(1000)
            }
        }
    }

    private fun getTimerFlow(): Flow<Int> {
        return flow {
            var seconds = 0
            while (seconds < 5) {
                println("Emitted: $seconds")
                emit(seconds++)
                delay(1000)
            }
        }
    }
}