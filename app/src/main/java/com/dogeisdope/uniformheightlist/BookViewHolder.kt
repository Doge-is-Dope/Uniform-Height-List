package com.dogeisdope.uniformheightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dogeisdope.uniformheightlist.databinding.ListItemBookBinding

class BookViewHolder(
    maxHeight: Int,
    private val binding: ListItemBookBinding,
    private val onBookClick: (Book) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.layoutParams.height = maxHeight
    }

    fun bind(book: Book) {
        with(binding) {
            titleLength.text = book.title.length.toString()
            title.text = book.title
            root.setOnClickListener { onBookClick(book) }
        }
    }

    companion object {
        fun from(parent: ViewGroup, onBookClick: (Book) -> Unit, maxHeight: Int): BookViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBookBinding.inflate(layoutInflater, parent, false)
            return BookViewHolder(maxHeight, binding, onBookClick)
        }
    }
}