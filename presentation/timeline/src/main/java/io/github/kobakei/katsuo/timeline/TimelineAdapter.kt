package io.github.kobakei.katsuo.timeline

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.timeline.databinding.TimelineItemBinding

class MainAdapter(
        context: Context
) : RecyclerView.Adapter<MainViewHolder>() {

    val articles = mutableListOf<Article>()

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = TimelineItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val binding = holder.binding
        binding.item = articles[position]
    }

}

class MainViewHolder(val binding: TimelineItemBinding) : RecyclerView.ViewHolder(binding.root)