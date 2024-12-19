package users

import java.awt.Dimension
import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea
import kotlin.concurrent.thread

class Display {

    private val textArea = JTextArea().apply {
        isEditable = false
        font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
        margin = Insets(32, 32, 32, 32)
    }

    fun show() {

        val scrollPane = JScrollPane(textArea)
        JFrame().apply {
            isVisible = true
            size = Dimension(600, 600)
            add(scrollPane)
        }

        UsersRepository.getInstance("qwerty").registerObserver(this)
    }

    fun onChanged(users: List<User>) {
        users
            .joinToString("\n")
            .let { textArea.text = it }
    }
}