package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Location

class BookmarkAdapter(var bookmarks: List<Location>, private val onItemClick: (Location) -> Unit, private val onDeleteClick: (Location) -> Unit) :
    RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookmarkName: TextView = itemView.findViewById(R.id.text_bookmark_name)
        val text_bookmark_name_remove: TextView = itemView.findViewById(R.id.text_bookmark_name_remove)

        init {
            bookmarkName.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(bookmarks[position])
                }
            }

            text_bookmark_name_remove.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClick(bookmarks[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bookmark, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = bookmarks[position]
        holder.bookmarkName.text = bookmark.name

    }

    override fun getItemCount(): Int {
        return bookmarks.size
    }
    fun updateBookmarks(newBookmarks: List<Location>) {
        bookmarks = newBookmarks
        notifyDataSetChanged()
    }
}