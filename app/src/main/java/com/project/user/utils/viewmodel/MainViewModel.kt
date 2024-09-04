package com.project.user.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.user.data.database.Student
import com.project.user.data.repository.DataRepository
import kotlinx.coroutines.launch

class MainViewModel (private val repository: DataRepository) : ViewModel() {

    private val _alumniList = MutableLiveData<List<Student>>()
    val alumniList: LiveData<List<Student>> get() = _alumniList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _addStudentStatus = MutableLiveData<Result<Boolean>>()
    val addStudentStatus: LiveData<Result<Boolean>> get() = _addStudentStatus

    fun loadAllStudent() {
        viewModelScope.launch {
            val alumni = repository.getAllStudent()
            _alumniList.postValue(alumni)
        }
    }

    fun addStudent(alumni: Student) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val existStudent = repository.checkStudent(alumni.number)
                if (existStudent != null) {
                    _addStudentStatus.postValue(Result.success(false))
                } else {
                    repository.insertStudent(alumni)
                    _addStudentStatus.postValue(Result.success(true))
                }
            } catch (e: Exception) {
                _addStudentStatus.postValue(Result.failure(e))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun removeStudent(alumni: Student) {
        viewModelScope.launch {
            repository.deleteStudent(alumni)
            loadAllStudent()
        }
    }

    fun updateStudent(alumni: Student) {
        viewModelScope.launch {
            repository.updateStudent(alumni)
            loadAllStudent()
        }
    }

}