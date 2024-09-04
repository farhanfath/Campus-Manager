package com.project.user.view.home.data

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.user.data.database.Student
import com.project.user.databinding.ActivityDataBinding
import com.project.user.utils.StudentAdapter
import com.project.user.utils.viewmodel.MainViewModel
import com.project.user.utils.viewmodel.ViewModelFactory

class DataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBinding
    private lateinit var adapter: StudentAdapter

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Data Mahasiswa"

        adapter = StudentAdapter(emptyList(), viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.studentList.observe(this) { studentList ->
            adapter.updateData(studentList)
            checkIfListIsEmpty(studentList)
        }

        binding.addDataBtn.setOnClickListener {
            val intent = Intent(this, CrudDataActivity::class.java)
            intent.putExtra("type", CrudDataActivity.TYPE_INSERT)
            startActivity(intent)
        }

        viewModel.loadAllStudent()
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadAllStudent()
    }

    private fun checkIfListIsEmpty(studentList: List<Student>) {
        if (studentList.isEmpty()) {
            binding.recyclerView.visibility = View.GONE
            binding.emptyFrame.visibility = View.VISIBLE
        } else {
            binding.recyclerView.visibility = View.VISIBLE
            binding.emptyFrame.visibility = View.GONE
        }
    }
}