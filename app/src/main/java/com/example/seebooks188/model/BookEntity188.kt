
package com.example.seebooks188.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "books_188",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity188::class,
            parentColumns = ["id_188"],
            childColumns = ["user_id_188"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["user_id_188"])]
)
data class BookEntity188(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_188")
    val id188: Int = 0,

    @ColumnInfo(name = "user_id_188")
    val userId188: Int,

    @ColumnInfo(name = "judul_188")
    val judul188: String,

    @ColumnInfo(name = "pengarang_188")
    val pengarang188: String,

    @ColumnInfo(name = "tahun_188")
    val tahun188: Int
)
