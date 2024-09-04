package com.project.user.view.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.user.databinding.ActivityHomeBinding
import com.project.user.utils.Utility
import com.project.user.view.auth.LoginActivity
import com.project.user.view.home.data.CrudDataActivity
import com.project.user.view.home.data.DataActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Dashboard"

        with(binding) {
            infoBtn.setOnClickListener {
                startActivity(Intent(this@HomeActivity, InfoActivity::class.java))
            }
            addDataBtn.setOnClickListener {
                val intent = Intent(this@HomeActivity, CrudDataActivity::class.java)
                intent.putExtra("type", CrudDataActivity.TYPE_INSERT)
                startActivity(intent)
            }
            seeDataBtn.setOnClickListener {
                startActivity(Intent(this@HomeActivity, DataActivity::class.java))
            }
            logoutBtn.setOnClickListener {
                showLogoutConfirmationDialog()
            }
        }
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                logoutHandler()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun logoutHandler() {
        Utility.logout()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}