package com.example.tradebrainsassignment.Views.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tradebrainsassignment.Models.Company
import com.example.tradebrainsassignment.Models.CompanyInfo
import com.example.tradebrainsassignment.Models.DailyInfo
import com.example.tradebrainsassignment.R
import com.example.tradebrainsassignment.Repository.SearchRepository
import com.example.tradebrainsassignment.Views.Adapters.CompanyAdapter
import com.example.tradebrainsassignment.Views.Adapters.WatchListAdapter
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_watchlist.*
import kotlinx.android.synthetic.main.header_layout.*

class WatchListFragment:Fragment(R.layout.fragment_watchlist) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    private fun initViews(){
        pageName.text=getString(R.string.watchlist)
        getCompanies()
    }
    private fun getCompanies(){
        val list:MutableList<Company> = mutableListOf()
        val adapter = WatchListAdapter(list) {
            list.remove(it)
            removeFromDb(it)
            rv_table.adapter?.notifyDataSetChanged()
        }
        rv_table.layoutManager=LinearLayoutManager(context)
        rv_table.adapter=adapter
        SearchRepository.getInstance(context!!).getCompanies()
            .map {
                val feeds = it
                it.map {company->
                    SearchRepository.getInstance(context!!).getCompany(company.symbol)
                            .subscribe(
                                {
                                    val string = it?.listOfPrices
                                    val date = it?.metaData

                                    val company = Company(
                                        company.symbol,
                                        company.name,
                                        string?.getOrDefault(date?.latestDate, string.values.first())?.sharePrice ?:"Unavailable"
                                    )
                                    adapter.addData(company)
                                    list.add(company)
                                },
                                {
                                    Log.e("TAG", "Error getCompanies: $it",)
                                })
                }
            }
            .subscribe(
                {
                    it.map { CompositeDisposable().addAll(it) }
                },
                {
                    Log.e("TAG", "initViews: $it")
                }
            ).also {
                CompositeDisposable().add(it)
            }
    }
    fun removeFromDb(company: Company){

    }
}