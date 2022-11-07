package com.sports.cricket.service.`interface`

import com.sports.cricket.service.model.CricketModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("nzin01312019187360.json")
    suspend fun getMatchDetails(): Response<CricketModel>;
}