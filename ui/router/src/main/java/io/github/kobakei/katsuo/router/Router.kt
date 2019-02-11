package io.github.kobakei.katsuo.router

import android.app.Activity
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Author

class Router(
    val detail: DetailRouter,
    val author: AuthorRouter
)

interface DetailRouter {
    fun navigateToDetail(activity: Activity, article: Article)
}

interface AuthorRouter {
    fun navigateToAuthor(activity: Activity, author: Author)
}