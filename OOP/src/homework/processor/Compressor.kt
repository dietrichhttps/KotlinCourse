package homework.processor

class Compressor : DataProcessor("Compressor"), Transformable {

    override fun transform(data: String): String {
        return "$processorName преобразовал данные: compressed_$data"
    }
}