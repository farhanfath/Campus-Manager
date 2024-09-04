package com.project.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.user.utils.Utility
import com.project.user.view.auth.LoginActivity
import com.project.user.view.home.HomeActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        MainScope().launch {
            delay(5000)
            navigateToNextScreen()
        }
    }

    private fun navigateToNextScreen() {
        val nextActivity = if (Utility.auth.currentUser != null) {
            HomeActivity::class.java
        } else {
            LoginActivity::class.java
        }
        startActivity(Intent(this@SplashActivity, nextActivity))
        finish()
    }
}