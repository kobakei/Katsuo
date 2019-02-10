package io.github.kobakei.katsuo.router

import android.app.Activity
import io.github.kobakei.katsuo.entity.Article

interface DetailRouter {
    fun navigateToDetail(activity: Activity, article: Article)
}