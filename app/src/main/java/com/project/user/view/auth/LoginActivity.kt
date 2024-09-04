package com.project.user.view.auth

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.user.databinding.ActivityLoginBinding
import com.project.user.utils.viewmodel.AuthViewModel
import com.project.user.utils.viewmodel.ViewModelFactory
import com.project.user.view.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {
            loginHandler()
        }

        binding.toRegisterBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginHandler() {
        val email = binding.emailEt.text.toString()
        val password = binding.passwordEt.text.toString()

        when {
            email.isEmpty() -> {
                binding.emailLayout.error = "Email tidak boleh kosong"
            }
            password.isEmpty() -> {
                binding.passwordLayout.error = "Password tidak boleh kosong"
            }
            else -> {
                showLoading(true)
                viewModel.loginUser(email, password)
                viewModel.authResult.observe(this) { result ->
                    result.onSuccess { userId ->
                        showLoading(false)
                        showSuccessDialog()
                    }.onFailure {
                        showLoading(false)
                        showErrorDialog()
                    }
                }
            }
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle("Success")
            .setMessage("Berhasil Masuk!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                navigateToHome()
            }
            .show()
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Gagal Masuk. harap Coba Lagi")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun showLoading(onLoading: Boolean) {
        binding.loadingFrame.visibility = if (onLoading) View.VISIBLE else View.GONE
    }
}