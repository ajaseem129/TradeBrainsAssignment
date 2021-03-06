package com.example.tradebrainsassignment.Retrofit

import com.example.tradebrainsassignment.Models.CompanyInfo
import com.example.tradebrainsassignment.Models.SearchResponse
import com.example.tradebrainsassignment.Utils.Const
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("query")
    fun search(
        @Query("function") function: String,
        @Query("keywords") keyword: String,
        @Query("apikey") apiKey: String = Const.API_KEY
    ):Single<SearchResponse>

    @GET("query")
    fun getLatestInfo(
        @Query("function") function: String,
        @Query("symbol") keyword: String,
        @Query("apikey") apiKey: String = Const.API_KEY
    ):Single<CompanyInfo>
}