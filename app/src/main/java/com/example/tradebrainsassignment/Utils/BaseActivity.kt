package com.example.tradebrainsassignment.Utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity:AppCompatActivity() {
    private val compositeDisposable:CompositeDisposable= CompositeDisposable()

    fun addDisposable(disposable:Disposable){
        compositeDisposable.add(disposable)
    }
}