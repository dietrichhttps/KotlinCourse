package concurrency

import entities.Book
import kotlinx.coroutines.*
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.concurrent.Executors
import javax.swing.*

object Display {

    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private val scope = CoroutineScope(CoroutineName("My coroutine") + dispatcher)

    private val infoArea = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load book").apply {
        addActionListener {
            isEnabled = false
            infoArea.text = "Loading book information...\n"

            val jobs = mutableListOf<Deferred<Book>>()
            repeat(10) {
                scope.async {
                    val book = loadBook()
                    infoArea.append("Book $it: ${book.title}\nYear: ${book.year}\nGenre: ${book.genre}\n\n")
                    book
                }.also { jobs.add(it) }
            }
            scope.launch {
                jobs.awaitAll()
                    .forEach(::println)
                isEnabled = true
            }
        }
    }
    private val timerLabel = JLabel("Time: 00:00")
    private val topPanel = JPanel(BorderLayout()).apply {
        add(timerLabel, BorderLayout.WEST)
        add(loadButton, BorderLayout.EAST)
    }

    private val mainFrame = JFrame("Book and Author info").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(infoArea), BorderLayout.CENTER)
        size = Dimension(400, 300)
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                scope.cancel()
            }
        })
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private fun longOperation() {
        mutableListOf<Int>().apply {
            repeat(300_000) {
                add(0, it)
            }
        }
    }

    private suspend fun loadBook(): Book {
        withContext(Dispatchers.Default) {
            longOperation()
        }
        return Book("1984", 1949, "Dystopia")
    }

    private fun startTimer() {
        scope.launch {
            var totalSeconds = 0
            while (true) {
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerLabel.text = String.format("Time: %02d:%02d", minutes, seconds)
                delay(1000)
                totalSeconds++
            }
        }
    }
}

