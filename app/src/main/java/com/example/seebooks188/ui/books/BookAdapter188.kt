
package com.example.seebooks188.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seebooks188.databinding.ItemBookBinding
import com.example.seebooks188.model.BookEntity188

class BookAdapter188(
    private var books188: List<BookEntity188>,
    private val onEdit188: (BookEntity188) -> Unit,
    private val onDelete188: (BookEntity188) -> Unit
) : RecyclerView.Adapter<BookAdapter188.BookViewHolder188>() {

    inner class BookViewHolder188(
        val binding188: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding188.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder188 {

        val binding188 = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BookViewHolder188(binding188)
    }

    override fun onBindViewHolder(
        holder: BookViewHolder188,
        position: Int
    ) {
        val book188 = books188[position]

        holder.binding188.tvBookTitle188.text = book188.judul188
        holder.binding188.tvBookAuthor188.text =
            "Pengarang: ${book188.pengarang188}"
        holder.binding188.tvBookYear188.text =
            "Tahun terbit: ${book188.tahun188}"

        holder.binding188.btnEditBook188.setOnClickListener {
            onEdit188(book188)
        }

        holder.binding188.btnDeleteBook188.setOnClickListener {
            onDelete188(book188)
        }
    }

    override fun getItemCount(): Int = books188.size

    fun updateBooks188(newBooks188: List<BookEntity188>) {
        books188 = newBooks188
        notifyDataSetChanged()
    }
}
