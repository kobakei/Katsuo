package io.github.kobakei.katsuo.author

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Author
import io.github.kobakei.katsuo.repository.ArticleRepository
import kotlinx.coroutines.launch

class AuthorViewModel(context: Context) : ViewModel() {

    private val articleRepository = ArticleRepository(context)

    val author = MutableLiveData<Author>()
    val articles = MutableLiveData<List<Article>>()

    val articleClick = MutableLiveData<Article>()

    fun loadArticles(author: Author) {
        this.author.value = author
        author.let {
            viewModelScope.launch {
                articles.value = articleRepository.getArticlesByAuthor(author)
            }
        }
    }

    fun onArticleClick(article: Article) {
        articleClick.postValue(article)
    }
}