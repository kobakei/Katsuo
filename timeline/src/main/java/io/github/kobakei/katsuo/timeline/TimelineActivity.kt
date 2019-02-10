package io.github.kobakei.katsuo.timeline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kobakei.katsuo.entity.Photo
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

        binding.recyclerView.adapter = MainAdapter(this, listOf(
            Photo("title 1", "url 1"),
            Photo("title 2", "url 2"),
            Photo("title 3", "url 3")
        ))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
