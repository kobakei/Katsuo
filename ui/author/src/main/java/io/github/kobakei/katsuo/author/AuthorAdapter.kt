package io.github.kobakei.katsuo.author

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.kobakei.katsuo.author.databinding.AuthorHeaderBinding
import io.github.kobakei.katsuo.author.databinding.AuthorItemBinding
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Author

class AuthorAdapter(context: Context, val viewModel: AuthorViewModel) : RecyclerView.Adapter<AuthorViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    var author: Author? = null
    var articles = mutableListOf<Article>()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = AuthorHeaderBinding.inflate(inflater, parent, false)
                AuthorHeaderViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = AuthorItemBinding.inflate(inflater, parent, false)
                binding.viewModel = viewModel
                AuthorItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = articles.size + 1

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        when (holder) {
            is AuthorHeaderViewHolder -> {
                holder.binding.author = author
                holder.binding.executePendingBindings()
            }
            is AuthorItemViewHolder -> {
                holder.binding.article = articles[position - 1]
                holder.binding.executePendingBindings()
            }
        }
    }

    companion object {
        private const val TYPE_HEADER = 1
        private const val TYPE_ITEM = 2
    }
}

sealed class AuthorViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
class AuthorHeaderViewHolder(val binding: AuthorHeaderBinding) : AuthorViewHolder(binding)
class AuthorItemViewHolder(val binding: AuthorItemBinding) : AuthorViewHolder(binding)
