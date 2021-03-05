package com.example.tradebrainsassignment.Views.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tradebrainsassignment.R
import kotlinx.android.synthetic.main.header_layout.*

class WatchListFragment:Fragment(R.layout.fragment_watchlist) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    private fun initViews(){
        pageName.text=getString(R.string.watchlist)
    }
}