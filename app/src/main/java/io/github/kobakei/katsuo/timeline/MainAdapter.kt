package io.github.kobakei.katsuo.timeline

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kobakei.katsuo.databinding.MainItemBinding
import io.github.kobakei.katsuo.entity.Photo

class MainAdapter(
        context: Context,
        private val items: List<Photo>
) : RecyclerView.Adapter<MainViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val binding = holder.binding
        binding.item = items[position]
    }

}

class MainViewHolder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root)