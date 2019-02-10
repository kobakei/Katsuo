package io.github.kobakei.katsuo.repository

import android.content.Context
import io.github.kobakei.katsuo.api.apiClient
import io.github.kobakei.katsuo.database.createDb
import io.github.kobakei.katsuo.entity.Article

class ArticleRepository(private val context: Context) {

    private val articleDao = createDb(context).articleDao()

    /**
     * 優先度は、room => api
     */
    suspend fun getArticles(): List<Article> {
        val articles = articleDao.getAll()
        if (articles.isNotEmpty()) {
            return articles
        }
        val articles2 = apiClient().getArticles().await()
        articleDao.insertAll(*articles2.articles.toTypedArray())
        return articles2.articles
    }

}