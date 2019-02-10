package io.github.kobakei.katsuo.entity

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val id: Long,
    val name: String,
    val image: Image
)