package com.example.tradebrainsassignment.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tradebrainsassignment.R
import com.example.tradebrainsassignment.Utils.BaseActivity
import com.example.tradebrainsassignment.Views.Fragments.TradebrainsFragment
import com.example.tradebrainsassignment.Views.Fragments.WatchListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : BaseActivity() {

    companion object{
        val TAG= MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(TradebrainsFragment())
        val navigation = findViewById<BottomNavigationView>(R.id.fragmentTabs)
        navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.watchlist->{
                    loadFragment(WatchListFragment())
                }
                else->{
                    loadFragment(TradebrainsFragment())
                }

            }
        }
    }
    private fun loadFragment(fragment: Fragment):Boolean{
        return run {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
            true
        }
    }
}