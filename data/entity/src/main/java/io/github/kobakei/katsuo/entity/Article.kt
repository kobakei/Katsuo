package io.github.kobakei.katsuo.entity

data class Article(
        val id: Long,
        val title: String,
        val body: String,
        val image: Image,
        val author: Author
)