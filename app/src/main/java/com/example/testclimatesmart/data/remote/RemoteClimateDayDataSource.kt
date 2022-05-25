package com.example.testclimatesmart.data.remote

import com.example.testclimatesmart.data.local.ClimateCity


interface RemoteClimateDayDataSource {

    suspend fun getForecastLastFiveDays(
        lat: Double,
        lon: Double,
        units: String
    ): ClimateCity

}