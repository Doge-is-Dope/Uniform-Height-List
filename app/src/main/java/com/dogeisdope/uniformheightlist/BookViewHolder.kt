package com.dogeisdope.uniformheightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dogeisdope.uniformheightlist.databinding.ListItemBookBinding

class BookViewHolder(
    private val binding: ListItemBookBinding,
    private val onBookClick: (Book) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        binding.root.setOnClickListener { onBookClick(book) }
        updateBook(book)
    }

    fun updateBook(book: Book) {
        with(binding) {
            titleLength.text = book.title.length.toString()
            title.text = book.title
            root.layoutParams.height = book.maxHeight
        }
    }

    companion object {
        fun from(parent: ViewGroup, onBookClick: (Book) -> Unit): BookViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBookBinding.inflate(layoutInflater, parent, false)
            return BookViewHolder(binding, onBookClick)
        }
    }
}