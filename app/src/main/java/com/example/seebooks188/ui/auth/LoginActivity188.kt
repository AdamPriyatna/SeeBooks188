
package com.example.seebooks188.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.seebooks188.data.AuthRepository188
import com.example.seebooks188.database.AppDatabase188
import com.example.seebooks188.databinding.ActivityLoginBinding
import com.example.seebooks188.ui.dashboard.DashboardActivity188
import com.example.seebooks188.utils.SessionManager188
import kotlinx.coroutines.launch

class LoginActivity188 : AppCompatActivity() {

    private lateinit var binding188: ActivityLoginBinding
    private lateinit var authRepository188: AuthRepository188
    private lateinit var sessionManager188: SessionManager188

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding188 = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding188.root)

        val database188 = AppDatabase188.getDatabase188(this)

        authRepository188 = AuthRepository188(
            database188.userDao188()
        )

        sessionManager188 = SessionManager188(this)

        binding188.btnLogin188.setOnClickListener {
            loginUser188()
        }

        binding188.tvGoRegister188.setOnClickListener {
            startActivity(
                Intent(this, RegisterActivity188::class.java)
            )
        }
    }

    private fun loginUser188() {

        val username188 =
            binding188.etUsernameLogin188.text.toString().trim()

        val password188 =
            binding188.etPasswordLogin188.text.toString()

        if (username188.isEmpty() || password188.isEmpty()) {
            Toast.makeText(
                this,
                "Username dan password wajib diisi",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        lifecycleScope.launch {
            try {
                val user188 = authRepository188.login188(
                    username188,
                    password188
                )

                if (user188 != null) {

                    sessionManager188.saveLogin188(
                        user188.id188,
                        user188.username188
                    )

                    Toast.makeText(
                        this@LoginActivity188,
                        "Login berhasil!",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this@LoginActivity188,
                            DashboardActivity188::class.java
                        )
                    )

                    finish()

                } else {
                    Toast.makeText(
                        this@LoginActivity188,
                        "Username atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@LoginActivity188,
                    "Terjadi kesalahan: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
