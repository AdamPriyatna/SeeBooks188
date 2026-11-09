
package com.example.seebooks188.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.seebooks188.model.BookEntity188

@Dao
interface BookDao188 {

    @Insert
    suspend fun insertBook188(book188: BookEntity188): Long

    @Update
    suspend fun updateBook188(book188: BookEntity188)

    @Delete
    suspend fun deleteBook188(book188: BookEntity188)

    @Query("""
        SELECT * FROM books_188
        WHERE user_id_188 = :userId188
        ORDER BY id_188 DESC
    """)
    suspend fun getBooksByUser188(
        userId188: Int
    ): List<BookEntity188>

    @Query("""
        SELECT * FROM books_188
        WHERE user_id_188 = :userId188
        AND (
            pengarang_188 LIKE '%' || :keyword188 || '%'
            OR CAST(tahun_188 AS TEXT) LIKE '%' || :keyword188 || '%'
        )
        ORDER BY id_188 DESC
    """)
    suspend fun searchBooks188(
        userId188: Int,
        keyword188: String
    ): List<BookEntity188>

    @Query("""
        SELECT COUNT(*) FROM books_188
        WHERE user_id_188 = :userId188
    """)
    suspend fun countBooks188(
        userId188: Int
    ): Int
}
