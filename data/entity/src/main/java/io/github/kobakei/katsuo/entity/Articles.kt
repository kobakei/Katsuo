package io.github.kobakei.katsuo.entity

import kotlinx.serialization.Serializable

@Serializable
data class Articles(
    val articles: List<Article>
)