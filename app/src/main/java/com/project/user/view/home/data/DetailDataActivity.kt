package com.project.user.view.home.data

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.user.R
import com.project.user.data.database.Student
import com.project.user.databinding.ActivityDetailDataBinding

class DetailDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Data"

        val student = intent.getParcelableExtra<Student>("student")

        binding.apply {
            binding.numberEt.setText(student?.number)
            binding.usernameEt.setText(student?.name)
            binding.birthDateEt.setText(student?.birthDate)
            binding.genderEt.setText(student?.gender)
            binding.addressEt.setText(student?.address)
        }
    }
}