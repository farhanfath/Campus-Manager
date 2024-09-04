package com.project.user.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: String,
    val name: String,
    val birthDate: String,
    val gender: String,
    val address: String
) : Parcelable