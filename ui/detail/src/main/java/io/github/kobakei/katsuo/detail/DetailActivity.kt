package io.github.kobakei.katsuo.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import io.github.kobakei.katsuo.detail.databinding.DetailActivityBinding
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.router.Router
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private val router: Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel

        val article: Article = intent.getParcelableExtra("article")
        detailViewModel.article = article

        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.authorClick.observe(this, Observer {
            router.author.navigateToAuthor(this, it)
        })
    }
}
