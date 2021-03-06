package io.github.kobakei.katsuo.timeline

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kobakei.katsuo.entity.Ad
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.repository.AdRepository
import io.github.kobakei.katsuo.repository.ArticleRepository
import kotlinx.coroutines.*
import timber.log.Timber

data class TimelineData(
    val articles: List<Article>,
    val ad: Ad?
)

class TimelineViewModel(
    private val articleRepo: ArticleRepository,
    private val adRepo: AdRepository
) : ViewModel() {

    val timelineData = MutableLiveData<TimelineData>()

    val progressVisibility = MutableLiveData<Int>().apply { postValue(View.VISIBLE) }

    val reloadVisibility = MutableLiveData<Int>().apply { postValue(View.GONE) }

    val articleClick = MutableLiveData<Article>()

    /**
     * 複数のデータを取得して表示するサンプル
     * ・並列実行: suspend blockをasyncで囲む
     * ・例外処理: supervisorScope.launchで囲む
     * ・エラーを無視: asyncの中をさらにrunCatchingで囲み、ResultをgetOrNullで変換
     */
    fun loadData() {
        viewModelScope.launch {
            supervisorScope {
                runCatching {
                    val articlesAsync = async { articleRepo.getArticles() }
                    val adAsync = async {
                        runCatching { adRepo.getTimelineAd() }.getOrNull()
                    }
                    TimelineData(articlesAsync.await(), adAsync.await())
                }.fold(
                    onSuccess = {
                        timelineData.postValue(it)
                    },
                    onFailure = {
                        reloadVisibility.postValue(View.VISIBLE)
                    }
                )
                progressVisibility.postValue(View.GONE)
            }
        }
    }

    fun onItemClick(article: Article) {
        Timber.v("click: ${article.title}")
        articleClick.postValue(article)
    }

    fun onReloadClick() {
        progressVisibility.postValue(View.VISIBLE)
        reloadVisibility.postValue(View.GONE)
        loadData()
    }
}