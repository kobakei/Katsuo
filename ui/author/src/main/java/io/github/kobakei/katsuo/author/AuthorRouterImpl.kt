package io.github.kobakei.katsuo.author

import android.app.Activity
import android.content.Intent
import io.github.kobakei.katsuo.entity.Author
import io.github.kobakei.katsuo.router.AuthorRouter

class AuthorRouterImpl : AuthorRouter {
    override fun navigateToAuthor(activity: Activity, author: Author) {
        val intent = Intent(activity, AuthorActivity::class.java).apply {
            putExtra("author", author)
        }
        activity.startActivity(intent)
    }
}