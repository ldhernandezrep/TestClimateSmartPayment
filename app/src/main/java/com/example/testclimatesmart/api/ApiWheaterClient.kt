package com.example.testclimatesmart.api

import com.example.testclimatesmart.data.ClimateCity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWheaterClient {

    @GET("data/2.5/forecast")
    suspend fun getForecastLastFiveDays(
        @Query("lat") country_code: Double,
        @Query("lon") cinema: Double,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): ClimateCity

}