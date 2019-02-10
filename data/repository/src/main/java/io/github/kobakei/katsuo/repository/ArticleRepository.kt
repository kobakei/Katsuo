package io.github.kobakei.katsuo.repository

import io.github.kobakei.katsuo.api.apiClient
import io.github.kobakei.katsuo.entity.Article

class ArticleRepository {

    suspend fun getArticles(): List<Article> {
        val articles = apiClient().getArticles().await()
        return articles.articles
    }

}