package com.dogeisdope.uniformheightlist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        viewModel.getBooks(layoutInflater, binding.bookList)
        viewModel.books.observe(this) { (maxHeight, books) ->
            val adapter = BookAdapter(maxHeight) { book ->
                Timber.tag("test").d("Clicked on book: ${book.title}")
            }
            binding.bookList.adapter = adapter
            adapter.submitList(books)
        }
    }

    private fun setUpRefreshButton() {
        binding.refreshButton.setOnClickListener {
            viewModel.getBooks(layoutInflater, binding.bookList)
        }
    }
}