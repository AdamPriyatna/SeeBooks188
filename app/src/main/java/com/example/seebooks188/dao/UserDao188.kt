
package com.example.seebooks188.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.seebooks188.model.UserEntity188

@Dao
interface UserDao188 {

    @Insert
    suspend fun insertUser188(user188: UserEntity188): Long

    @Query("SELECT * FROM users_188 WHERE username_188 = :username188 LIMIT 1")
    suspend fun getUserByUsername188(
        username188: String
    ): UserEntity188?

    @Query("SELECT * FROM users_188 WHERE id_188 = :userId188 LIMIT 1")
    suspend fun getUserById188(
        userId188: Int
    ): UserEntity188?

    @Query("SELECT COUNT(*) FROM users_188 WHERE username_188 = :username188")
    suspend fun checkUsername188(
        username188: String
    ): Int
}
