package com.project.user.view.auth

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.user.databinding.ActivityRegisterBinding
import com.project.user.utils.viewmodel.AuthViewModel
import com.project.user.utils.viewmodel.ViewModelFactory
import com.project.user.view.home.HomeActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.toLoginBtn.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            registerHandler()
        }
    }

    private fun registerHandler() {
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
                viewModel.registerAndLoginUser(email, password)
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
            .setMessage("Berhasil Daftar Akun")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                navigateToHome()
            }
            .show()
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Gagal Mendaftar. harap Coba Lagi")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun showLoading(onLoading: Boolean) {
        binding.loadingFrame.visibility = if (onLoading) View.VISIBLE else View.GONE
    }
}