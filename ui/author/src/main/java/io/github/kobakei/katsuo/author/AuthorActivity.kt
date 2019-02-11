package io.github.kobakei.katsuo.author

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import io.github.kobakei.katsuo.author.databinding.AuthorActivityBinding
import io.github.kobakei.katsuo.entity.Author
import org.koin.android.viewmodel.ext.android.viewModel

class AuthorActivity : AppCompatActivity() {

    private lateinit var binding: AuthorActivityBinding
    private val authorViewModel: AuthorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.author_activity)
        binding.lifecycleOwner = this
        binding.viewModel = authorViewModel

        binding.recyclerView.adapter = AuthorAdapter(this)

        val author: Author = intent.getParcelableExtra("author")
        authorViewModel.loadArticles(author)
    }
}
