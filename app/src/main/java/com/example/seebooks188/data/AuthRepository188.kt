
package com.example.seebooks188.data

import com.example.seebooks188.dao.UserDao188
import com.example.seebooks188.model.UserEntity188
import java.security.MessageDigest

class AuthRepository188(
    private val userDao188: UserDao188
) {

    private fun hashPassword188(password188: String): String {
        val bytes188 = MessageDigest
            .getInstance("SHA-256")
            .digest(password188.toByteArray())

        return bytes188.joinToString("") {
            "%02x".format(it)
        }
    }

    suspend fun register188(
        username188: String,
        password188: String
    ): Boolean {
        val usernameClean188 = username188.trim()

        if (usernameClean188.isEmpty() || password188.isEmpty()) {
            return false
        }

        if (userDao188.checkUsername188(usernameClean188) > 0) {
            return false
        }

        val user188 = UserEntity188(
            username188 = usernameClean188,
            password188 = hashPassword188(password188)
        )

        userDao188.insertUser188(user188)
        return true
    }

    suspend fun login188(
        username188: String,
        password188: String
    ): UserEntity188? {
        val user188 = userDao188.getUserByUsername188(
            username188.trim()
        ) ?: return null

        return if (
            user188.password188 == hashPassword188(password188)
        ) {
            user188
        } else {
            null
        }
    }
}
