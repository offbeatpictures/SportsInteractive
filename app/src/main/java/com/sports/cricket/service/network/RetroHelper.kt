package com.sports.cricket.service.network

import com.sports.cricket.service.`interface`.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroHelper {

    private var retrofit: Retrofit? = null;
    private const val BASE_URL: String = "https://demo.sportz.io/";
    val apiInterface: ApiInterface = getRetroInstance().create(ApiInterface::class.java)

    private fun getRetroInstance(): Retrofit = synchronized(this) {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        return retrofit!!
    }

}