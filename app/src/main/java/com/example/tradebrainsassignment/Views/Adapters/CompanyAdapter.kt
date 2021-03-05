package com.example.tradebrainsassignment.Views.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tradebrainsassignment.Models.Company
import com.example.tradebrainsassignment.R
import kotlinx.android.synthetic.main.company_item.view.*

class CompanyAdapter(
    companiesList:List<Company>,
    private val addItem:(company:Company)->Unit,
    private val removeItem:(company:Company)->Unit
): RecyclerView.Adapter<CompanyAdapter.CompanyVH>() {

    private var companies= companiesList?: mutableListOf<Company>()

    fun setData(companiesList: List<Company>){
        this.companies=companiesList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyVH {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.company_item,parent,false)
        return CompanyVH(view)
    }

    override fun onBindViewHolder(holder: CompanyVH, position: Int) {
        companies[position].let {
            holder.onBind(
                it)
        }
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    class CompanyVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val item: ImageButton = itemView.item
        private val name: TextView = itemView.tv_name
        fun onBind(company: Company){
            val sharePrice =String.format("%2f",company.sharePrice.toDouble())
            name.text=itemView.context.getString(
                R.string.search_result,
                company.name,
                sharePrice
            )
            item.setOnClickListener {
                it.isSelected=!it.isSelected
            }
        }
    }
}