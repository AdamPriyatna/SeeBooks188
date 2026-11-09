
package com.example.seebooks188.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_188")
data class UserEntity188(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_188")
    val id188: Int = 0,

    @ColumnInfo(name = "username_188")
    val username188: String,

    @ColumnInfo(name = "password_188")
    val password188: String
)
