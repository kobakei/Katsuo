package io.github.kobakei.katsuo.entity

import kotlinx.serialization.Serializable

@Serializable
data class Ads(
    val timeline: Ad
)

@Serializable
data class Ad(
    val id: Long,
    val description: String,
    val image: AdImage
)

@Serializable
data class AdImage(
    val large: String
)