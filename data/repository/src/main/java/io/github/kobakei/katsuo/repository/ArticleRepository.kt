package io.github.kobakei.katsuo.repository

import io.github.kobakei.katsuo.api.apiClient
import io.github.kobakei.katsuo.database.ArticleDao
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Author

/**
 * 記事のリポジトリクラス
 * ローカルDBとAPIからデータを取得する。取得の優先度は、room => api。
 */
class ArticleRepository(private val articleDao: ArticleDao) {

    /**
     * すべての記事を取得する
     */
    suspend fun getArticles(): List<Article> {
        val articles = articleDao.getAll()
        if (articles.isNotEmpty()) {
            return articles
        }
        val articles2 = apiClient().getArticlesAsync().await()
        articleDao.insertAll(*articles2.articles.toTypedArray())
        return articles2.articles
    }

    /**
     * 指定した著者のすべての記事を取得する
     */
    suspend fun getArticlesByAuthor(author: Author): List<Article> =
        articleDao.getByAuthorId(author.id)
}