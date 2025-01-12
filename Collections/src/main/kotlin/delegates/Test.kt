@file:OptIn(ExperimentalEncodingApi::class)

package delegates

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    val user = User()
    user.a = 10
//    user.password = "1234"
//    user.credCardNumber = "4276 0000 0000 0000"
//    println(user.password)
//    println(user.credCardNumber)
}

class User {

    var a by observable(0) { old: Int, new: Int ->
        println("Old value: $old, new value: $new")
    }

    var password: String by encrypted()

    var credCardNumber: String by EncryptedProperty()
}

fun encrypted() = EncryptedProperty()

class EncryptedProperty : ReadWriteProperty<Any, String> {

    private var encryptedValue: String = ""

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        println("Getting value, encoded: $encryptedValue")
        val decoded = String(Base64.decode(encryptedValue))
        println("Getting value, decoded: $decoded")
        return decoded
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("New value: $value")
        val encoded = Base64.encode(value.toByteArray())
        println("Encoded value: $encoded")
        encryptedValue = encoded
    }
}

fun <T> observable(
    initialValue: T,
    onChanged: (oldValue: T, newValue: T) -> Unit
) = ObservableProperty(initialValue, onChanged)

class ObservableProperty<T>(
    initialValue: T,
    private val onChanged: (oldValue: T, newValue: T) -> Unit
) : ReadWriteProperty<Any, T> {

    private var currentValue = initialValue

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return currentValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val oldValue = currentValue
        currentValue = value
        onChanged(oldValue, value)
    }
}