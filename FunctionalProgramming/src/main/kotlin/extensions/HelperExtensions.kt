package extensions

inline fun <R, T> Iterable<T>.transform(operation: (T) -> R): List<R> {
    val result = mutableListOf<R>()
    for (value in this) {
        result.add(operation(value))
    }
    return result
}

inline fun <T> Iterable<T>.filter(isSuitable: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (value in this) {
        if (isSuitable(value)) {
            result.add(value)
        }
    }
    return result
}

inline fun <T> Iterable<T>.myForEach(operation: (T) -> Unit) {for (value in this) operation(value)}