package users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("age") val age: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("id") val id: Int,
    @SerialName("last_name") val lastName: String
)