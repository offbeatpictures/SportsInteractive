package com.sports.cricket.service.model


import com.google.gson.annotations.SerializedName

data class Players (
    @SerializedName("Batting")
    val batting: Batting,
    @SerializedName("Bowling")
    val bowling: Bowling,
    @SerializedName("Iskeeper")
    val iskeeper: Boolean,
    @SerializedName("Iscaptain")
    val isCaptain:Boolean,
    @SerializedName("Name_Full")
    val nameFull: String,
    @SerializedName("Position")
    val position: String
) : java.io.Serializable