package io.github.kobakei.katsuo.entity

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val thumbnail: String,
    val large: String
)