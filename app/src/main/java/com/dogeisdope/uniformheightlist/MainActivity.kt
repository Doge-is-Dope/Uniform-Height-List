package com.dogeisdope.uniformheightlist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.dogeisdope.uniformheightlist.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpBookList()
        setUpRefreshButton()
    }

    private fun setUpBookList() {
        val adapter = BookAdapter {
            Timber.tag("test").d("Book clicked: $it")
        }
        binding.bookList.adapter = adapter

        viewModel.getBooks(layoutInflater, binding.bookList)
        viewModel.books.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setUpRefreshButton() {
        binding.refreshButton.setOnClickListener {
            viewModel.getBooks(layoutInflater, binding.bookList)
        }
    }
}