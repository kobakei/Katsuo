package io.github.kobakei.katsuo.author

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kobakei.katsuo.author.databinding.AuthorActivityBinding
import io.github.kobakei.katsuo.entity.Author
import io.github.kobakei.katsuo.router.Router
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class AuthorActivity : AppCompatActivity() {

    private lateinit var binding: AuthorActivityBinding
    private val authorViewModel: AuthorViewModel by viewModel()
    private lateinit var adapter: AuthorAdapter
    private val router: Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.author_activity)
        binding.lifecycleOwner = this
        binding.viewModel = authorViewModel
        observeViewModel()

        val author: Author = intent.getParcelableExtra("author")
        authorViewModel.loadArticles(author)

        adapter = AuthorAdapter(this, authorViewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        authorViewModel.author.observe(this, Observer {
            adapter.author = it
            adapter.notifyDataSetChanged()
        })
        authorViewModel.articles.observe(this, Observer {
            adapter.articles.clear()
            adapter.articles.addAll(it)
            adapter.notifyDataSetChanged()
        })
        authorViewModel.articleClick.observe(this, Observer {
            router.detail.navigateToDetail(this, it)
        })
    }
}
