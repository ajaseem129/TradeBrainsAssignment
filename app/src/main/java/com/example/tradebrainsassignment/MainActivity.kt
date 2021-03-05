package com.example.tradebrainsassignment

import android.os.Bundle
import android.util.Log
import com.example.tradebrainsassignment.Repository.SearchRepository
import com.example.tradebrainsassignment.Retrofit.ApiClient
import com.example.tradebrainsassignment.Utils.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = ApiClient().getClient()
        SearchRepository.getInstance().search("tcs")
            .subscribe({
                Log.d("TAG", "onCreate: $it")
            },
            {
                Log.e("TAG", "onCreate: $it")
            }).also {
                addDisposable(it)
            }

    }
}