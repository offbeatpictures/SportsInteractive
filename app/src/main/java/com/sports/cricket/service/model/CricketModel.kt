package com.sports.cricket.service.model


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CricketModel(
    @SerializedName("Innings")
    val innings: List<Inning>,
    @SerializedName("Matchdetail")
    val matchdetail: Matchdetail,
    @SerializedName("Notes")
    val notes: Notes,
    @SerializedName("Nuggets")
    val nuggets: List<String>,
    @SerializedName("Teams")
    val teams: JsonObject
)