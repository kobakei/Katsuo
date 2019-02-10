package io.github.kobakei.katsuo.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.repository.ArticleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TimelineViewModel : ViewModel(), CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job

    // TODO DI
    val repo = ArticleRepository()

    val articles = MutableLiveData<List<Article>>()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun loadData() {
        launch(Dispatchers.Main) {
            articles.postValue(repo.getArticles())
        }
    }

}