package homework.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProductCategory {

    @SerialName("electronics")
    ELECTRONICS,

    @SerialName("clothing")
    CLOTHING,

    @SerialName("home decor")
    HOME_DECOR,

    @SerialName("beauty")
    BEAUTY,

    @SerialName("sports")
    SPORTS
}