package io.github.kobakei.katsuo.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kobakei.katsuo.entity.Ad
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.repository.AdRepository
import io.github.kobakei.katsuo.repository.ArticleRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class TimelineViewModel(
    private val articleRepo: ArticleRepository,
    private val adRepo: AdRepository
) : ViewModel() {

    val articles = MutableLiveData<List<Article>>()
    val ad = MutableLiveData<Ad>()

    val articleClick = MutableLiveData<Article>()

    fun loadData() {
        viewModelScope.launch {
            articles.postValue(articleRepo.getArticles())
        }
        viewModelScope.launch {
            ad.postValue(adRepo.getTimelineAd())
        }
    }

    fun onItemClick(article: Article) {
        Timber.v("click: ${article.title}")
        articleClick.postValue(article)
    }
}