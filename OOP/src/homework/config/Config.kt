package homework.config

class Config {

    var timeout: Int = 30
        set(value: Int) {
            if (value !in 1..300) {
                println("Ошибка: Время ожидания должно быть в диапазоне от 1 до 300 секунд. Установлено значение по умолчанию.")
            } else {
                field = value
            }
        }

    var maxRetries: Int = 3
        set(value: Int) {
            if (value < 0) {
                println("Ошибка: Максимальное количество повторных попыток не может быть отрицательным. Установлено значение по умолчанию.")
            } else {
                field = value
            }
        }

    var loggingLevel: LogLevel = LogLevel.INFO
        set(value: LogLevel) {
            if (value == LogLevel.FATAL || value == LogLevel.TRACE) {
                println("Ошибка: Уровень $value недоступен.")
            } else {
                field = value
            }
        }

    val isDebugMode: Boolean
        get() = loggingLevel == LogLevel.DEBUG


    val isProductionMode: Boolean
        get() = loggingLevel == LogLevel.ERROR

    fun printConfig() {
        println(
            "Время ожидания: $timeout секунд\nМаксимальное количество повторных попыток: $maxRetries\nУровень логирования: $loggingLevel\nРежим отладки: $isDebugMode\nРежим продакшн: $isProductionMode"
        )
    }
}