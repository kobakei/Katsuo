package io.github.kobakei.katsuo.detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import io.github.kobakei.katsuo.detail.databinding.DetailActivityBinding
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.presentation.common.NonNullExtra
import io.github.kobakei.katsuo.router.Router
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private val router: Router by inject()

    private val article: Article by NonNullExtra()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel

        detailViewModel.article = article

        observeViewModel()

        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeViewModel() {
        detailViewModel.authorClick.observe(this, Observer {
            router.author.navigateToAuthor(this, it)
        })
        detailViewModel.shareClick.observe(this, Observer {
            Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        fun createIntent(activity: Activity, article: Article): Intent =
                Intent(activity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity::article.name, article)
                }
    }
}
