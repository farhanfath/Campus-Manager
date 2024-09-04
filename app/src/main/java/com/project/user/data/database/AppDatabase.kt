package com.project.user.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Student::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (Instance == null) {
                synchronized(AppDatabase::class.java) {
                    Instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_data").build()
                }
            }
            return Instance
        }
    }
    abstract fun studentDao(): StudentDao
}