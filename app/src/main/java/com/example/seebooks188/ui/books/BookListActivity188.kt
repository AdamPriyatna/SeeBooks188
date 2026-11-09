
package com.example.seebooks188.ui.books

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seebooks188.database.AppDatabase188
import com.example.seebooks188.databinding.ActivityBookListBinding
import com.example.seebooks188.model.BookEntity188
import com.example.seebooks188.utils.SessionManager188
import kotlinx.coroutines.launch

class BookListActivity188 : AppCompatActivity() {

    private lateinit var binding188: ActivityBookListBinding
    private lateinit var adapter188: BookAdapter188
    private lateinit var sessionManager188: SessionManager188

    private val database188 by lazy {
        AppDatabase188.getDatabase188(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding188 = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding188.root)

        sessionManager188 = SessionManager188(this)

        if (!sessionManager188.isLoggedIn188()) {
            finish()
            return
        }

        adapter188 = BookAdapter188(
            emptyList(),

            onEdit188 = { book188 ->
                openEditForm188(book188)
            },

            onDelete188 = { book188 ->
                confirmDelete188(book188)
            }
        )

        binding188.rvBooks188.layoutManager =
            LinearLayoutManager(this)

        binding188.rvBooks188.adapter = adapter188

        binding188.btnAddBook188.setOnClickListener {
            val intent188 = Intent(this, BookFormActivity188::class.java)
            startActivity(intent188)
        }

        binding188.etSearchBook188.addTextChangedListener(
            object : android.text.TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    searchBooks188(s.toString())
                }

                override fun afterTextChanged(s: android.text.Editable?) {}
            }
        )

        loadBooks188()
    }

    private fun loadBooks188() {
        val userId188 = sessionManager188.getUserId188()

        lifecycleScope.launch {
            val books188 =
                database188.bookDao188().getBooksByUser188(userId188)

            adapter188.updateBooks188(books188)
        }
    }

    private fun searchBooks188(keyword188: String) {
        val userId188 = sessionManager188.getUserId188()

        lifecycleScope.launch {
            val books188 = if (keyword188.isBlank()) {
                database188.bookDao188().getBooksByUser188(userId188)
            } else {
                database188.bookDao188().searchBooks188(
                    userId188,
                    keyword188
                )
            }

            adapter188.updateBooks188(books188)
        }
    }

    private fun openEditForm188(book188: BookEntity188) {
        val intent188 = Intent(this, BookFormActivity188::class.java)

        intent188.putExtra("book_id_188", book188.id188)
        intent188.putExtra("book_title_188", book188.judul188)
        intent188.putExtra("book_author_188", book188.pengarang188)
        intent188.putExtra("book_year_188", book188.tahun188)

        startActivity(intent188)
    }

    private fun confirmDelete188(book188: BookEntity188) {
        AlertDialog.Builder(this)
            .setTitle("Hapus Buku")
            .setMessage("Apakah kamu yakin ingin menghapus buku \"${book188.judul188}\"?")
            .setPositiveButton("Hapus") { _, _ ->
                deleteBook188(book188)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun deleteBook188(book188: BookEntity188) {
        lifecycleScope.launch {
            try {
                database188.bookDao188().deleteBook188(book188)

                Toast.makeText(
                    this@BookListActivity188,
                    "Buku berhasil dihapus",
                    Toast.LENGTH_SHORT
                ).show()

                loadBooks188()

            } catch (e: Exception) {
                Toast.makeText(
                    this@BookListActivity188,
                    "Gagal menghapus buku: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (::sessionManager188.isInitialized &&
            sessionManager188.isLoggedIn188()
        ) {
            loadBooks188()
        }
    }
}
