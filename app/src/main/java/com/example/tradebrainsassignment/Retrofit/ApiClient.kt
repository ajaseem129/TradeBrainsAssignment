package com.example.tradebrainsassignment.Retrofit

import com.example.tradebrainsassignment.Utils.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient{

    private var retrofit:Retrofit?=null
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient=OkHttpClient.Builder()
            .addInterceptor(logger).build()
    fun getClient():Retrofit{
        if (retrofit==null){
            retrofit=Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
        }
        return retrofit!!
    }
}