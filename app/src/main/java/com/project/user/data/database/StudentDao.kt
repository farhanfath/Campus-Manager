package com.project.user.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("SELECT * FROM student WHERE number = :number LIMIT 1")
    suspend fun checkStudent(number: String): Student?

    @Query("SELECT * FROM student")
    suspend fun getAllStudent(): List<Student>
}