import week.DaysOfWeek

fun main() {
    val daysOfWeek = DaysOfWeek.entries
    for (day in daysOfWeek) {
        println(day.title)
    }
}