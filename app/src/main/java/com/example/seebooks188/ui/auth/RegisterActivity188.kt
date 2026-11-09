
package com.example.seebooks188.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.seebooks188.data.AuthRepository188
import com.example.seebooks188.database.AppDatabase188
import com.example.seebooks188.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity188 : AppCompatActivity() {

    private lateinit var binding188: ActivityRegisterBinding
    private lateinit var authRepository188: AuthRepository188

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding188 = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding188.root)

        val database188 = AppDatabase188.getDatabase188(this)
        authRepository188 = AuthRepository188(database188.userDao188())

        binding188.btnRegister188.setOnClickListener {
            registerUser188()
        }

        binding188.tvGoLogin188.setOnClickListener {
            startActivity(Intent(this, LoginActivity188::class.java))
            finish()
        }
    }

    private fun registerUser188() {
        val username188 = binding188.etUsername188.text.toString().trim()
        val password188 = binding188.etPassword188.text.toString()
        val confirmPassword188 =
            binding188.etConfirmPassword188.text.toString()

        if (username188.isEmpty() || password188.isEmpty() ||
            confirmPassword188.isEmpty()
        ) {
            Toast.makeText(this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show()
            return
        }

        if (password188.length < 6) {
            Toast.makeText(this, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show()
            return
        }

        if (password188 != confirmPassword188) {
            Toast.makeText(this, "Konfirmasi password tidak cocok", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                val success188 = authRepository188.register188(
                    username188,
                    password188
                )

                if (success188) {
                    Toast.makeText(
                        this@RegisterActivity188,
                        "Registrasi berhasil! Silakan login.",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this@RegisterActivity188,
                            LoginActivity188::class.java
                        )
                    )
                    finish()
                } else {
                    Toast.makeText(
                        this@RegisterActivity188,
                        "Username sudah digunakan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@RegisterActivity188,
                    "Registrasi gagal: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
