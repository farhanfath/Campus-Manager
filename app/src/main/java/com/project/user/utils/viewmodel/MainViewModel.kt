package com.project.user.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.user.data.database.Student
import com.project.user.data.repository.DataRepository
import kotlinx.coroutines.launch

class MainViewModel (private val repository: DataRepository) : ViewModel() {

    private val _studentList = MutableLiveData<List<Student>>()
    val studentList: LiveData<List<Student>> get() = _studentList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _addStudentStatus = MutableLiveData<Result<Boolean>>()
    val addStudentStatus: LiveData<Result<Boolean>> get() = _addStudentStatus

    fun loadAllStudent() {
        viewModelScope.launch {
            val student = repository.getAllStudent()
            _studentList.postValue(student)
        }
    }

    fun addStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val existStudent = repository.checkStudent(student.number)
                if (existStudent != null) {
                    _addStudentStatus.postValue(Result.success(false))
                } else {
                    repository.insertStudent(student)
                    _addStudentStatus.postValue(Result.success(true))
                }
            } catch (e: Exception) {
                _addStudentStatus.postValue(Result.failure(e))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun removeStudent(student: Student) {
        viewModelScope.launch {
            repository.deleteStudent(student)
            loadAllStudent()
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.updateStudent(student)
            loadAllStudent()
        }
    }

}