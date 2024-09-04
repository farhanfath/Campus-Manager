package com.project.user.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.user.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel (private val repository: DataRepository) : ViewModel() {

    private val _authResult = MutableLiveData<Result<String>>()
    val authResult: LiveData<Result<String>> get() = _authResult

    fun registerAndLoginUser(email: String, password: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.registerAndLoginUser(email, password)
            }
            _authResult.value = result
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.loginUser(email, password)
            }
            _authResult.value = result
        }
    }
}