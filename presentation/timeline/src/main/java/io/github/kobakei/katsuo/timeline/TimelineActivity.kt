package io.github.kobakei.katsuo.timeline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kobakei.katsuo.timeline.databinding.TimelineActivityBinding

class TimelineActivity : AppCompatActivity() {

    private lateinit var binding: TimelineActivityBinding
    private lateinit var viewModel: TimelineViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.timeline_activity)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get()
        binding.viewModel = viewModel
        observeViewModel()

        adapter = MainAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.loadData()
    }

    private fun observeViewModel() {
        viewModel.articles.observe(this, Observer {
            adapter.articles.clear()
            adapter.articles.addAll(it ?: listOf())
            adapter.notifyDataSetChanged()
        })
    }
}
