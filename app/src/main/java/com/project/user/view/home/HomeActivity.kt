package com.project.user.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.user.databinding.ActivityHomeBinding
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
        }
    }
}