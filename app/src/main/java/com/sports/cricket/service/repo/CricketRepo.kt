package com.sports.cricket.service.repo

import com.sports.cricket.service.model.CricketModel
import com.sports.cricket.service.network.RetroHelper
import com.sports.cricket.service.network.RetroHelper.apiInterface
import retrofit2.Response

class CricketRepo {
    suspend fun getMatchDetails(): Response<CricketModel> = apiInterface.getMatchDetails();
}