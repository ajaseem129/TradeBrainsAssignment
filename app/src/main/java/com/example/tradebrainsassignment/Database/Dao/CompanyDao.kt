package com.example.tradebrainsassignment.Database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tradebrainsassignment.Database.Entity.Company
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company")
    fun getCompanies():Single<List<Company>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompanyData(
        company: Company
    ):Completable
}