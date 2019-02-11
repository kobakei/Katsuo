package io.github.kobakei.katsuo.detail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Author

class DetailViewModel : ViewModel() {

    lateinit var article: Article

    val authorClick = MutableLiveData<Author>()
    val shareClick = MutableLiveData<Unit>()

    fun onAuthorClick(@Suppress("UNUSED_PARAMETER") view: View) {
        authorClick.postValue(article.author)
    }

    fun onShareClick(@Suppress("UNUSED_PARAMETER") view: View) {
        shareClick.postValue(Unit)
    }
}