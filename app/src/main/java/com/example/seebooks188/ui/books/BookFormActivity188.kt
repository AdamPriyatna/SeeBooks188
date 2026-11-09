
package com.example.seebooks188.ui.books

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.seebooks188.database.AppDatabase188
import com.example.seebooks188.databinding.ActivityBookFormBinding
import com.example.seebooks188.model.BookEntity188
import com.example.seebooks188.utils.SessionManager188
import kotlinx.coroutines.launch

class BookFormActivity188 : AppCompatActivity() {

    private lateinit var binding188: ActivityBookFormBinding
    private lateinit var sessionManager188: SessionManager188

    private val database188 by lazy {
        AppDatabase188.getDatabase188(this)
    }

    private var bookId188 = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding188 = ActivityBookFormBinding.inflate(layoutInflater)
        setContentView(binding188.root)

        sessionManager188 = SessionManager188(this)

        bookId188 = intent.getIntExtra("book_id_188", -1)

        if (bookId188 != -1) {
            binding188.tvFormTitle188.text = "Edit Buku"
            binding188.btnSaveBook188.text = "SIMPAN PERUBAHAN"

            binding188.etTitle188.setText(
                intent.getStringExtra("book_title_188")
            )
            binding188.etAuthor188.setText(
                intent.getStringExtra("book_author_188")
            )
            binding188.etYear188.setText(
                intent.getIntExtra("book_year_188", 0).toString()
            )
        }

        binding188.btnSaveBook188.setOnClickListener {
            saveBook188()
        }
    }

    private fun saveBook188() {
        val title188 = binding188.etTitle188.text.toString().trim()
        val author188 = binding188.etAuthor188.text.toString().trim()
        val yearText188 = binding188.etYear188.text.toString().trim()

        if (title188.isEmpty() || author188.isEmpty() || yearText188.isEmpty()) {
            Toast.makeText(
                this,
                "Semua kolom wajib diisi",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val year188 = yearText188.toIntOrNull()

        if (year188 == null || year188 < 1 || year188 > 9999) {
            Toast.makeText(
                this,
                "Tahun terbit tidak valid",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val userId188 = sessionManager188.getUserId188()

        if (userId188 == -1) {
            Toast.makeText(this, "Silakan login kembali", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        lifecycleScope.launch {
            try {
                val book188 = BookEntity188(
                    id188 = if (bookId188 == -1) 0 else bookId188,
                    userId188 = userId188,
                    judul188 = title188,
                    pengarang188 = author188,
                    tahun188 = year188
                )

                if (bookId188 == -1) {
                    database188.bookDao188().insertBook188(book188)
                    Toast.makeText(
                        this@BookFormActivity188,
                        "Buku berhasil ditambahkan",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    database188.bookDao188().updateBook188(book188)
                    Toast.makeText(
                        this@BookFormActivity188,
                        "Buku berhasil diperbarui",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                finish()

            } catch (e: Exception) {
                Toast.makeText(
                    this@BookFormActivity188,
                    "Gagal menyimpan: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
