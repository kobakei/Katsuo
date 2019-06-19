package io.github.kobakei.katsuo.repository

import com.google.common.truth.Truth
import io.github.kobakei.katsuo.api.ApiClient
import io.github.kobakei.katsuo.database.ArticleDao
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Articles
import io.github.kobakei.katsuo.entity.Author
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ArticleRepositoryTest {

    private val articles = listOf<Article>(
        mockk(),
        mockk(),
        mockk()
    )

    @Test
    fun getArticles_isCorrectWithoutCache() {
        val dao = mockk<ArticleDao>()
        coEvery { dao.getAll() } returns listOf()
        coEvery { dao.insertAll(any(), any(), any()) } returns Unit

        val api = mockk<ApiClient>()
        coEvery { api.getArticlesAsync() } returns Articles(
            articles = articles
        )

        val repo = ArticleRepository(dao, api)
        runBlocking {
            Truth.assertThat(repo.getArticles())
                .containsExactlyElementsIn(articles)
        }
    }

    @Test
    fun getArticlesByAuthor_isCorrect() {
        val author = Author(
            id = 1,
            name = "John",
            image = null
        )
        val dao = mockk<ArticleDao>()
        coEvery { dao.getByAuthorId(author.id) } returns articles

        val api = mockk<ApiClient>()

        runBlocking {
            val repo = ArticleRepository(dao, api)
            Truth.assertThat(repo.getArticlesByAuthor(author))
                .containsExactlyElementsIn(articles)
        }
    }
}