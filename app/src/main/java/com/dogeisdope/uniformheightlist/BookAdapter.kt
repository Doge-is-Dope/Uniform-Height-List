package com.dogeisdope.uniformheightlist

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BookAdapter(private val onBookClick: (Book) -> Unit) :
    ListAdapter<Book, BookViewHolder>(BookDiffCallback) {

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder.from(parent, onBookClick)

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            (payloads[0] as Bundle).getParcelable<Book>("title")?.let {
                holder.updateBook(it)
            }
        }
    }
}

object BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Book, newItem: Book) = oldItem == newItem

    override fun getChangePayload(oldItem: Book, newItem: Book): Any = Bundle().apply {
        if (oldItem.title != newItem.title) putParcelable("title", newItem)
    }
}


