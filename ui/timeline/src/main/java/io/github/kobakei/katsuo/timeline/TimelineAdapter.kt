package io.github.kobakei.katsuo.timeline

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.kobakei.katsuo.entity.Ad
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.timeline.databinding.TimelineAdItemBinding
import io.github.kobakei.katsuo.timeline.databinding.TimelineArticleItemBinding

sealed class TimelineViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
class TimelineArticleViewHolder(val binding: TimelineArticleItemBinding): TimelineViewHolder(binding)
class TimelineAdViewHolder(val binding: TimelineAdItemBinding): TimelineViewHolder(binding)

/**
 * タイムライン画面のアダプター
 * この画面は、記事のリストがまず並び、一番下に広告が出ます。
 */
class TimelineAdapter(
    context: Context,
    private val timelineViewModel: TimelineViewModel
) : RecyclerView.Adapter<TimelineViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    private val articles: List<Article>
        get() = timelineViewModel.timelineData.value?.articles ?: listOf()

    private val ad: Ad?
        get() = timelineViewModel.timelineData.value?.ad

    override fun getItemViewType(position: Int): Int {
        return if (position < articles.size) {
            TYPE_ARTICLE
        } else {
            TYPE_AD
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        return when (viewType) {
            TYPE_ARTICLE -> {
                val binding = TimelineArticleItemBinding.inflate(inflater, parent, false)
                binding.viewModel = timelineViewModel
                TimelineArticleViewHolder(binding)
            }
            TYPE_AD -> {
                val binding = TimelineAdItemBinding.inflate(inflater, parent, false)
                TimelineAdViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        var count = articles.size
        if (ad != null) {
            count++
        }
        return count
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        when (holder) {
            is TimelineArticleViewHolder -> {
                val binding = holder.binding
                binding.item = articles[position]
                binding.executePendingBindings()
            }
            is TimelineAdViewHolder -> {
                val binding = holder.binding
                binding.ad = ad
                binding.executePendingBindings()
            }
        }
    }

    companion object {
        private const val TYPE_ARTICLE = 1
        private const val TYPE_AD = 2
    }

}
