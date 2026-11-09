
package com.example.seebooks188.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.seebooks188.dao.BookDao188
import com.example.seebooks188.dao.UserDao188
import com.example.seebooks188.model.BookEntity188
import com.example.seebooks188.model.UserEntity188

@Database(
    entities = [
        UserEntity188::class,
        BookEntity188::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase188 : RoomDatabase() {

    abstract fun userDao188(): UserDao188

    abstract fun bookDao188(): BookDao188

    companion object {

        @Volatile
        private var INSTANCE188: AppDatabase188? = null

        fun getDatabase188(context: Context): AppDatabase188 {

            return INSTANCE188 ?: synchronized(this) {

                val instance188 = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase188::class.java,
                    "seebooks_database_188"
                ).build()

                INSTANCE188 = instance188

                instance188
            }
        }
    }
}
