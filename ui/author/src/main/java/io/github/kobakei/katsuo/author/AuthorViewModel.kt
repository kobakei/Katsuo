package io.github.kobakei.katsuo.author

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Author
import io.github.kobakei.katsuo.repository.ArticleRepository
import kotlinx.coroutines.launch

class AuthorViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

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