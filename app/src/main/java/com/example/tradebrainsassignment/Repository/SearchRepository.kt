package com.example.tradebrainsassignment.Repository

import com.example.tradebrainsassignment.Models.SearchResponse
import com.example.tradebrainsassignment.Retrofit.ApiClient
import com.example.tradebrainsassignment.Retrofit.ApiInterface
import com.example.tradebrainsassignment.Utils.Const
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepository private constructor(
    private val apiInterface: ApiInterface
){
    companion object{
        private var instance: SearchRepository?=null
        fun getInstance():SearchRepository{
            return if (instance==null){
                val retrofit=ApiClient().getClient()
                val apiInterface= retrofit.create(ApiInterface::class.java)
                instance=SearchRepository(apiInterface)
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
}