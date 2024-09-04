package com.project.user.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.project.user.data.database.Student
import com.project.user.data.database.StudentDao

class DataRepository(
    private val context: Context,
    private val studentDao: StudentDao
) {

    // student database
    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun checkStudent(nim: String): Student? {
        return studentDao.checkStudent(nim)
    }

    suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }

    suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }

    suspend fun getAllStudent(): List<Student> {
        return studentDao.getAllStudent()
    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(
            context: Context,
            studentDao: StudentDao
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(context, studentDao)
            }.also { instance = it }
    }
}