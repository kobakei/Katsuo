package io.github.kobakei.katsuo.timeline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kobakei.katsuo.timeline.databinding.TimelineActivityBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TimelineActivity : AppCompatActivity() {

    private lateinit var binding: TimelineActivityBinding
    private val timelineViewModel: TimelineViewModel by viewModel()
    private lateinit var adapter: TimelineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.timeline_activity)
        binding.lifecycleOwner = this

        binding.viewModel = timelineViewModel
        observeViewModel()

        adapter = TimelineAdapter(this, timelineViewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        timelineViewModel.loadData()
    }

    private fun observeViewModel() {
        timelineViewModel.articles.observe(this, Observer {
            adapter.articles.clear()
            adapter.articles.addAll(it ?: listOf())
            adapter.notifyDataSetChanged()
        })
    }
}
