
package com.example.seebooks188.utils

import android.content.Context

class SessionManager188(context: Context) {

    private val preferences188 = context.getSharedPreferences(
        "seebooks_session_188",
        Context.MODE_PRIVATE
    )

    fun saveLogin188(userId188: Int, username188: String) {
        preferences188.edit()
            .putInt("user_id_188", userId188)
            .putString("username_188", username188)
            .putBoolean("is_logged_in_188", true)
            .apply()
    }

    fun getUserId188(): Int {
        return preferences188.getInt("user_id_188", -1)
    }

    fun getUsername188(): String? {
        return preferences188.getString("username_188", null)
    }

    fun isLoggedIn188(): Boolean {
        return preferences188.getBoolean("is_logged_in_188", false)
    }

    fun logout188() {
        preferences188.edit().clear().apply()
    }
}
