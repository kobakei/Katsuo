package io.github.kobakei.katsuo.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import io.github.kobakei.katsuo.detail.databinding.DetailActivityBinding
import io.github.kobakei.katsuo.entity.Article
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel

        val article: Article = intent.getParcelableExtra("article")
        detailViewModel.article = article
    }
}
