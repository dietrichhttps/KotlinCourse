package test

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val authorName: String,
    val year: Int
)
