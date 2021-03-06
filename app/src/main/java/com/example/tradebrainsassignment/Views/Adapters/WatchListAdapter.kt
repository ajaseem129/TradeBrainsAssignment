package com.example.tradebrainsassignment.Views.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tradebrainsassignment.Models.Company
import com.example.tradebrainsassignment.R
import kotlinx.android.synthetic.main.company_item.view.*
import kotlinx.android.synthetic.main.table_item.view.*

class WatchListAdapter(
    val companiesList:MutableList<Company>,
    val removeItem: (company:Company)->Unit
) : RecyclerView.Adapter<WatchListAdapter.WatchListVH>() {
    private var companies:MutableList<Company> = companiesList?: mutableListOf<Company>()

    fun setData(companiesList: MutableList<Company>){
        this.companies=companiesList
        notifyDataSetChanged()
    }
    fun addData(company: Company){
        this.companies.add(company)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListVH {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.table_item,parent,false)
        return WatchListVH(view)
    }

    override fun onBindViewHolder(holder: WatchListVH, position: Int) {
        companies[position]?.let {
            holder.onBind(
                it,
                removeItem
            )
        }
    }

    override fun getItemCount(): Int =companies.size-1
    class WatchListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val priceTV = itemView.tv_Cprice
        val nameTV = itemView.tv_Cname
        val removeIV=itemView.iv_remove
        fun onBind(
            company: Company,
            removeItem: (company:Company) -> Unit
        ) {
            nameTV.text = company?.name
            priceTV.text=company?.sharePrice
            removeIV.setOnClickListener {
                removeItem(company)
            }
        }
    }
}