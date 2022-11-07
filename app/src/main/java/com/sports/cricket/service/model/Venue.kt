package com.sports.cricket.service.model


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String
)