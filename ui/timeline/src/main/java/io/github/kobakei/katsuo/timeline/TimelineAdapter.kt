package io.github.kobakei.katsuo.timeline

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.timeline.databinding.TimelineItemBinding

class TimelineAdapter(
    context: Context,
    private val timelineViewModel: TimelineViewModel
) : RecyclerView.Adapter<TimelineViewHolder>() {

    val articles = mutableListOf<Article>()

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val binding = TimelineItemBinding.inflate(inflater, parent, false)
        binding.viewModel = timelineViewModel
        return TimelineViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        val binding = holder.binding
        binding.item = articles[position]
        binding.executePendingBindings()
    }

}

class TimelineViewHolder(val binding: TimelineItemBinding) : RecyclerView.ViewHolder(binding.root)
