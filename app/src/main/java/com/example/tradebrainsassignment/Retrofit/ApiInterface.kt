package com.example.tradebrainsassignment.Retrofit

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
}