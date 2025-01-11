package coldFlows

import hotFlows.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val scope = CoroutineScope(Executors.newCachedThreadPool().asCoroutineDispatcher())

fun main() {
    val flow = Repository.timer
    scope.launch {
//        flow.take(3)
//            .collect {
//                println(it)
//            }
    }
    scope.launch {
        flow
            .collect {
                println(it)
            }
        println("Finished")
    }
}