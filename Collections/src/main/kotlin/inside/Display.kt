package inside

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JTextArea

class Display {

    val textArea = JTextArea()

    val button = JButton().apply {
        addActionListener(ClickListener())
    }

    private inner class ClickListener : ActionListener {
        override fun actionPerformed(p0: ActionEvent?) {
            textArea.text = "Clicked"
        }
    }
}

