package com.example.tradebrainsassignment.Repository

import android.content.Context
import com.example.tradebrainsassignment.Database.AppDatabase
import com.example.tradebrainsassignment.Models.Company
import com.example.tradebrainsassignment.Models.SearchResponse
import com.example.tradebrainsassignment.Retrofit.ApiClient
import com.example.tradebrainsassignment.Retrofit.ApiInterface
import com.example.tradebrainsassignment.Utils.Const
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepository private constructor(
    private val apiInterface: ApiInterface,
    private val appDatabase: AppDatabase
){
    companion object{
        private var instance: SearchRepository?=null
        fun getInstance(context: Context):SearchRepository{
            return if (instance==null){
                val retrofit=ApiClient().getClient()
                val apiInterface= retrofit.create(ApiInterface::class.java)
                instance=SearchRepository(
                    apiInterface,
                    AppDatabase.getInstance(context!!)
                )
                instance!!
            }
            else{
                instance!!
            }
        }
    }
    private val apiKey= Const.API_KEY
    private val function= "SYMBOL_SEARCH"
    fun search(keyword:String)=
        apiInterface.search(
            function,
            keyword,
            apiKey
        )
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
    fun insertCompany(company: Company): Completable {
        return appDatabase.companyDao()
            .insertCompanyData(
                com.example.tradebrainsassignment.Database.Entity.Company(
                    symbol = company.symbol,
                    name = company.name,
                    sharePrice = company.sharePrice
                ))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
    fun getCompanies(): Single<List<com.example.tradebrainsassignment.Database.Entity.Company>> {
        return appDatabase.companyDao().getCompanies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}