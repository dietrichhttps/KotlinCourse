package dictionary

import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URI
import java.util.concurrent.Executors

object Repository {

    private const val BASE_URL = "https://api.api-ninjas.com/v1/dictionary?word="
    private const val API_KEY = "inatJobnk7PKjhFumlB2pg==4CBa2kLxGLQBtZ3O"
    private const val HEADER_KEY = "X-Api-Key"

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun loadDefinition(word: String): String {
        return withContext(Dispatchers.IO) {
            var connection: HttpURLConnection? = null
            try {
                val urlString = BASE_URL + word
                val url = URI(urlString).toURL()
                connection = (url.openConnection() as HttpURLConnection).apply {
                    addRequestProperty(HEADER_KEY, API_KEY)
                }
                val response = connection.inputStream.bufferedReader().readText()
                json.decodeFromString<Definition>(response).definition
            } catch (e: Exception) {
                ""
            } finally {
                connection?.disconnect()
            }
        }
    }
}

private val scope = CoroutineScope(Executors.newCachedThreadPool().asCoroutineDispatcher())

fun main() {
    scope.launch {
        while (true) {
            print("Enter word: ")
            val word = readln()
            val definition = Repository.loadDefinition(word)
            println(definition)
        }
    }
}