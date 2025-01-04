package dictionary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.awt.BorderLayout
import java.awt.Font
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

@Suppress("OPT_IN_USAGE")
object Display {

    private val queries = Channel<String>()
    private val state = MutableSharedFlow<ScreenState>()

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val repository = Repository

    private val enterWordLabel = JLabel("Enter word: ")
    private val searchField = JTextField(20).apply {
        addKeyListener(object : KeyAdapter() {
            override fun keyReleased(e: KeyEvent?) {
                loadDefinitions()
            }
        })
    }
    private val searchButton = JButton("Search").apply {
        addActionListener {
            loadDefinitions()
        }
    }
    private val resultArea = JTextArea(25, 50).apply {
        isEditable = false
        lineWrap = true
        wrapStyleWord = true
        font = Font("Liberation Sans", Font.PLAIN, 14)
    }
    private val topPanel = JPanel().apply {
        add(enterWordLabel)
        add(searchField)
        add(searchButton)
    }
    private val mainFrame = JFrame("Dictionary App").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(resultArea), BorderLayout.CENTER)
        pack()
    }

    private fun loadDefinitions() {
        scope.launch {
            queries.send(searchField.text.trim())
        }
    }

    fun show() {
        mainFrame.isVisible = true
    }

    init {
        queries.consumeAsFlow()
            .onEach {
                state.emit(ScreenState.Loading)
            }.debounce(500)
            .map {
                if (it.isEmpty()) {
                    state.emit(ScreenState.Initial)
                } else {
                    val result = repository.loadDefinition(it)
                    if (result.isNotEmpty()) {
                        state.emit(ScreenState.DefinitionsLoaded(result))
                    } else {
                        state.emit(ScreenState.NotFound)
                    }
                }
            }.launchIn(scope)

        state.onStart {
            emit(ScreenState.Initial)
        }.onEach {
            when (it) {
                is ScreenState.DefinitionsLoaded -> {
                    resultArea.text = it.definitions.joinToString("\n\n")
                    searchButton.isEnabled = true
                }

                ScreenState.Initial -> {
                    resultArea.text = ""
                    searchButton.isEnabled = false
                }

                ScreenState.Loading -> {
                    resultArea.text = "Loading..."
                    searchButton.isEnabled = false
                }

                ScreenState.NotFound -> {
                    resultArea.text = "Not found"
                    searchButton.isEnabled = true
                }
            }
        }
            .launchIn(scope)
    }
}

fun main() {
    System.setProperty("awt.useSystemAAFontSettings", "on")
    System.setProperty("swing.aatext", "true")
    Display.show()
}