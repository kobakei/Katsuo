package io.github.kobakei.katsuo.timeline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Author
import io.github.kobakei.katsuo.entity.Image
import io.github.kobakei.katsuo.timeline.databinding.TimelineActivityBinding

class TimelineActivity : AppCompatActivity() {

    private lateinit var binding: TimelineActivityBinding
    private lateinit var viewModel: TimelineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.timeline_activity)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get()
        binding.viewModel = viewModel

        val image = Image("", "")
        val author = Author(1, "Name", image)
        binding.recyclerView.adapter = MainAdapter(this, listOf(
            Article(1, "title 1", "url 1", image, author),
            Article(2, "title 2", "url 2", image, author),
            Article(3, "title 3", "url 3", image, author)
        ))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
