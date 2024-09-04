package com.project.user.utils

import android.content.Context
import com.project.user.data.database.AppDatabase
import com.project.user.data.repository.DataRepository

object Utility {
    fun provideRepository(context: Context): DataRepository {
        val studentDao = requireNotNull(AppDatabase.getDatabase(context)?.studentDao()) {
            "Failed to obtain alumniDao"
        }
        return DataRepository.getInstance(context, studentDao)
    }
}