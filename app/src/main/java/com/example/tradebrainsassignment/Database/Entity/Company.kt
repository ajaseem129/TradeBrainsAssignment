package com.example.tradebrainsassignment.Database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @PrimaryKey val symbol: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sharePrice") var sharePrice:String
)