package homework.logging

import java.text.SimpleDateFormat
import java.util.*

class LogEvent(val eventData: Any) {

    private val date: String = getCurrentDate()

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(Date())
    }

    override fun toString(): String {
        return "[$date]: $eventData"
    }
}