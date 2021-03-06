package com.example.tradebrainsassignment.Models

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("bestMatches")
    val companies:List<Company>
)
data class Company(
    @SerializedName("1. symbol")
    val symbol:String,
    @SerializedName("2. name")
    val name: String,
    @SerializedName("9. matchScore")
    var sharePrice: String
)
data class CompanyInfo(
    @SerializedName("Time Series (Daily)")
    val listOfPrices: MutableMap<String,DailyInfo>?=null,
    @SerializedName("Meta Data")
    val metaData:MetaData?=null
)
data class MetaData(
    @SerializedName("3. Last Refreshed")
    val latestDate:String?=null
)
data class DailyInfo(
    @SerializedName("4. close")
    val sharePrice: String?=null
)