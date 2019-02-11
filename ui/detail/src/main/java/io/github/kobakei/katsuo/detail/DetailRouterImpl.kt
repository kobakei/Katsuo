package io.github.kobakei.katsuo.detail

import android.app.Activity
import android.content.Intent
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.router.DetailRouter

class DetailRouterImpl : DetailRouter {

    override fun navigateToDetail(activity: Activity, article: Article) {
        val intent = DetailActivity.createIntent(activity, article)
        activity.startActivity(intent)
    }

}