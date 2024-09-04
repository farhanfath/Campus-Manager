package com.project.user.view.home.data

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.project.user.R
import com.project.user.data.database.Student
import com.project.user.databinding.ActivityCrudDataBinding
import com.project.user.utils.viewmodel.MainViewModel
import com.project.user.utils.viewmodel.ViewModelFactory
import java.util.Calendar

class CrudDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrudDataBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var student: Student
    private lateinit var currentEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrudDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val type = intent.getStringExtra("type")

        binding.birthDateEt.setOnClickListener { openCalendar(binding.birthDateEt) }
        binding.backBtn.setOnClickListener { finish() }

        when(type) {
            TYPE_INSERT -> {
                binding.title.text = getString(R.string.inputTitle)
                binding.saveDataBtn.setOnClickListener {
                    val insertStudent = createStudentFromInput()
                    insertDataHandler(insertStudent)
                }
            }
            TYPE_EDIT -> {
                binding.title.text = getString(R.string.updateTitle)
                student = intent.getParcelableExtra("student") ?: return
                setupUpdateView(student)
                binding.saveDataBtn.setOnClickListener {
                    val updatedStudent = createUpdatedStudent()
                    updateDataHandler(updatedStudent)
                }
            }
        }
    }

    private fun createStudentFromInput(): Student {
        val number = binding.numberEt.text.toString()
        val name = binding.usernameEt.text.toString()
        val birthDate = binding.birthDateEt.text.toString()
        val gender = binding.genderEt.text.toString()
        val address = binding.addressEt.text.toString()

        return Student(
            number = number,
            name = name,
            birthDate = birthDate,
            gender = gender,
            address = address
        )
    }

    private fun createUpdatedStudent(): Student {
        val updatedData = createStudentFromInput()
        return student.copy(
            number = updatedData.number,
            name = updatedData.name,
            birthDate = updatedData.birthDate,
            gender = updatedData.gender,
            address = updatedData.address
        )
    }

    private fun insertDataHandler(student: Student) {
        if (student.number.isNotEmpty() && student.name.isNotEmpty() && student.birthDate.isNotEmpty() && student.gender.isNotEmpty() && student.address.isNotEmpty()) {
            viewModel.addStudent(student)
            viewModel.isLoading.observe(this) { isLoading ->
                showProgressBar(isLoading)
            }
            viewModel.addStudentStatus.observe(this) { result ->
                result.onSuccess { success ->
                    if (success) {
                        Toast.makeText(this, "Berhasil Menambahkan Data Mahasiswa", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Gagal Menambahkan Data Mahasiswa", Toast.LENGTH_SHORT).show()
                    }
                }.onFailure {
                    Log.e("AddAlumniActivity", "Error adding Student data: ${it.message}")
                }
            }
        } else {
            Snackbar.make(binding.root, "Semua Kolom harus diisi", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupUpdateView(student: Student) {
        binding.numberEt.setText(student.number)
        binding.usernameEt.setText(student.name)
        binding.birthDateEt.setText(student.birthDate)
        binding.genderEt.setText(student.gender)
        binding.addressEt.setText(student.address)
    }

    private fun updateDataHandler(student: Student) {
        viewModel.updateStudent(student)
        finish()
        Toast.makeText(this, "Berhasil Mengupdate Data Mahasiswa", Toast.LENGTH_SHORT).show()
    }

    private fun openCalendar(editText: TextInputEditText) {
        currentEditText = editText

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                currentEditText.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun showProgressBar(onLoading: Boolean) {
        binding.progressBar.visibility = if (onLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val TYPE_INSERT = "insert"
        const val TYPE_EDIT = "edit"
    }
}