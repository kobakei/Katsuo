package io.github.kobakei.katsuo.timeline

import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.kobakei.katsuo.R
import io.github.kobakei.katsuo.databinding.MainActivityBinding
import io.github.kobakei.katsuo.entity.Photo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
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
