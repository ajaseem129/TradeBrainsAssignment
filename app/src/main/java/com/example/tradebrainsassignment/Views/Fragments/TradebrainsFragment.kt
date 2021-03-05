package com.example.tradebrainsassignment.Views.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.tradebrainsassignment.R
import kotlinx.android.synthetic.main.header_layout.*

class TradebrainsFragment : Fragment(R.layout.fragment_tradebrains) {
    companion object {
        val TAG= TradebrainsFragment::class.java.simpleName
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){


    }
}