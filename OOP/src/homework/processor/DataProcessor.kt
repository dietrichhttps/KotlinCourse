package homework.processor

abstract class DataProcessor(val processorName: String) {

    fun process(data: String): String {
        return "Обработчик: $processorName обработал данны"
    }
}