package com.sports.cricket.service.model


import com.google.gson.annotations.SerializedName

data class TeamInfoModel(
    @SerializedName("Name_Full")
    val nameFull: String,
    @SerializedName("Name_Short")
    val nameShort: String,
    @SerializedName("Players")
    val players: ArrayList<Players>
) : java.io.Serializable