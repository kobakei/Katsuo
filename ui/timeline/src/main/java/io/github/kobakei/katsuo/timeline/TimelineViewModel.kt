package io.github.kobakei.katsuo.timeline

import android.view.View
import androidx.lifecycle.*
import io.github.kobakei.katsuo.entity.Ad
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.repository.AdRepository
import io.github.kobakei.katsuo.repository.ArticleRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class TimelineViewModel(
    private val articleRepo: ArticleRepository,
    private val adRepo: AdRepository
) : ViewModel() {

    val articles = MutableLiveData<List<Article>>()
    val ad = MutableLiveData<Ad>()

    val progressVisibility: LiveData<Int> = Transformations.map(articles) {
        if (it?.isNotEmpty() == true) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    val articleClick = MutableLiveData<Article>()

    fun loadData() {
        viewModelScope.launch {
            val task1 = async {
                articleRepo.getArticles()
            }
            val task2 = async {
                adRepo.getTimelineAd()
            }
            val result1 = task1.await()
            val result2 = task2.await()
            articles.postValue(result1)
            ad.postValue(result2)
        }
    }

    fun onItemClick(article: Article) {
        Timber.v("click: ${article.title}")
        articleClick.postValue(article)
    }
}