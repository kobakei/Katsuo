package io.github.kobakei.katsuo.router

import android.app.Activity
import io.github.kobakei.katsuo.entity.Article

class Router(
    val detail: DetailRouter
)

interface DetailRouter {
    fun navigateToDetail(activity: Activity, article: Article)
}