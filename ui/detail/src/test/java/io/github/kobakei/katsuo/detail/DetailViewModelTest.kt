package io.github.kobakei.katsuo.detail

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.ArticleImage
import io.github.kobakei.katsuo.entity.Author
import io.github.kobakei.katsuo.entity.AuthorImage
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var view: View

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun onAuthorClick_isCorrect() {
        val author = Author(
            id = 123,
            name = "Hoge",
            image = AuthorImage(
                thumbnail = "",
                large = ""
            )
        )
        val viewModel = DetailViewModel().apply {
            article = Article(
                id = 1,
                title = "Title",
                body = "Body",
                image = ArticleImage(
                    thumbnail = "",
                    large = ""
                ),
                author = author
            )
        }
        viewModel.onAuthorClick(view)
        Truth.assertThat(viewModel.authorClick.value).isEqualTo(author)
    }
}