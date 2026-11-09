
package com.example.seebooks188.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.seebooks188.database.AppDatabase188
import com.example.seebooks188.databinding.ActivityDashboardBinding
import com.example.seebooks188.ui.auth.LoginActivity188
import com.example.seebooks188.utils.SessionManager188
import kotlinx.coroutines.launch
import com.example.seebooks188.ui.books.BookListActivity188


class DashboardActivity188 : AppCompatActivity() {

    private lateinit var binding188: ActivityDashboardBinding
    private lateinit var sessionManager188: SessionManager188

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding188 = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding188.root)

        sessionManager188 = SessionManager188(this)

        if (!sessionManager188.isLoggedIn188()) {
            openLogin188()
            return
        }

        binding188.tvWelcome188.text =
            "Welcome, ${sessionManager188.getUsername188()}!"

        loadTotalBooks188()

        binding188.btnMyBooks188.setOnClickListener {
            startActivity(
                Intent(this, BookListActivity188::class.java)
            )
        }

        binding188.btnLogout188.setOnClickListener {
            sessionManager188.logout188()

            Toast.makeText(
                this,
                "Berhasil logout",
                Toast.LENGTH_SHORT
            ).show()

            openLogin188()
        }
    }

    private fun loadTotalBooks188() {
        val userId188 = sessionManager188.getUserId188()
        val database188 = AppDatabase188.getDatabase188(this)

        lifecycleScope.launch {
            val total188 = database188.bookDao188()
                .countBooks188(userId188)

            binding188.tvTotalBooks188.text = total188.toString()
        }
    }

    private fun openLogin188() {
        val intent188 = Intent(this, LoginActivity188::class.java)
        intent188.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent188)
        finish()
    }

    override fun onResume() {
        super.onResume()

        if (::sessionManager188.isInitialized &&
            sessionManager188.isLoggedIn188()
        ) {
            loadTotalBooks188()
        }
    }
}
