package com.dogeisdope.uniformheightlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BookAdapter(private val itemHeight: Int, private val onBookClick: (Book) -> Unit) :
    ListAdapter<Book, BookViewHolder>(BookDiffCallback) {

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder.from(parent, onBookClick, itemHeight)
}

object BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Book, newItem: Book) = oldItem == newItem
}


