package com.sports.cricket.service.model


import com.google.gson.annotations.SerializedName

data class PartnershipCurrent(
    @SerializedName("Balls")
    val balls: String,
    @SerializedName("Batsmen")
    val batsmen: List<Batsmen>,
    @SerializedName("Runs")
    val runs: String
)