package com.example.tradebrainsassignment.Database.Dao

import androidx.room.*
import com.example.tradebrainsassignment.Database.Entity.Company
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company")
    fun getCompanies():Single<List<Company>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCompanyData(
        company: Company
    ):Completable
    @Delete
    fun deleteFromDb(
        company: Company
    ):Completable
}