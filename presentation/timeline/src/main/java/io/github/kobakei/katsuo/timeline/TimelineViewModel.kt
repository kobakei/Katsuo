package io.github.kobakei.katsuo.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.repository.ArticleRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class TimelineViewModel : ViewModel() {

    // TODO DI
    private val repo = ArticleRepository()

    val articles = MutableLiveData<List<Article>>()

    val articleClick = MutableLiveData<Article>()

    fun loadData() {
        viewModelScope.launch {
            articles.postValue(repo.getArticles())
        }
    }

    fun onItemClick(article: Article) {
        Timber.v("click: ${article.title}")
        articleClick.postValue(article)
    }
}