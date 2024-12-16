package homework.dogs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    @SerialName("breed") val breed: String,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("weight") val weight: Double
)