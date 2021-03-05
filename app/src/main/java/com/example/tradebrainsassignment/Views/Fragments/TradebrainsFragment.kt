package com.example.tradebrainsassignment.Views.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tradebrainsassignment.Database.AppDatabase
import com.example.tradebrainsassignment.Models.Company
import com.example.tradebrainsassignment.Models.SearchResponse
import com.example.tradebrainsassignment.R
import com.example.tradebrainsassignment.Repository.SearchRepository
import com.example.tradebrainsassignment.Views.Adapters.CompanyAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_tradebrains.*
import kotlinx.android.synthetic.main.header_layout.*

class TradebrainsFragment : Fragment(R.layout.fragment_tradebrains) {
    companion object {
        val TAG= TradebrainsFragment::class.java.simpleName
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    private fun initViews(){
        pageName.text=getString(R.string.trade_brains)
        val searchResults =mutableListOf<Company>()
        val adapter= CompanyAdapter(searchResults,
            {
                insertToDB(it)
            },
            {
                Log.e(TAG, "initViews: Remove item $it")
            })
        rv_searchResults.layoutManager=LinearLayoutManager(context)
        rv_searchResults.adapter=adapter
        et_searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                searchResults.clear()
                adapter.setData(searchResults)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                SearchRepository.getInstance(context!!).search(s.toString())
                    .subscribe(
                        {
                            searchResults.clear()
                            if (!it.companies.isNullOrEmpty()) {
                                searchResults.addAll(it.companies)
                                adapter.setData(searchResults)
                            }
                            else
                                Toast.makeText(context,getString(R.string.no_data),Toast.LENGTH_LONG).show()
                        },
                        {
                            Log.e(TAG, "onTextChanged: Errpr $it",)
                        }
                    )
                    .also {
                        CompositeDisposable().add(it)
                    }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun insertToDB(company:Company){
        SearchRepository.getInstance(context!!).insertCompany(company)
            .subscribe (
                {
                    Toast.makeText(context!!,getString(R.string.added_to_watchlist),Toast.LENGTH_LONG).show()
                },
                {
                    Log.e(TAG, "initViews: ERROR $it")
                })
            .also {
                CompositeDisposable().add(it)
            }
    }
}