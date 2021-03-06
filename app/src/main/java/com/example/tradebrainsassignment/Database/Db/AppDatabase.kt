package com.example.tradebrainsassignment.Database.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tradebrainsassignment.Database.Dao.CompanyDao
import com.example.tradebrainsassignment.Database.Entity.Company

@Database(entities = [Company::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun companyDao():CompanyDao
    companion object{
        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "trade-brains"
                ).build()
            }
            return appDatabase!!
        }
    }
}