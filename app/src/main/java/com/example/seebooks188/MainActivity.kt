
package com.example.seebooks188

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seebooks188.ui.auth.LoginActivity188
import com.example.seebooks188.ui.dashboard.DashboardActivity188
import com.example.seebooks188.utils.SessionManager188

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sessionManager188 = SessionManager188(this)

        val destination188 = if (sessionManager188.isLoggedIn188()) {
            DashboardActivity188::class.java
        } else {
            LoginActivity188::class.java
        }

        startActivity(Intent(this, destination188))
        finish()
    }
}
