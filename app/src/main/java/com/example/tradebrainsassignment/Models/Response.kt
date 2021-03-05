package com.example.tradebrainsassignment.Models

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
    val sharePrice: String
)