package com.example.testclimatesmart.data


interface RemoteClimateDayDataSource {

    suspend fun getForecastLastFiveDays(
        lat: Double,
        lon: Double,
        units: String
    ): ClimateCity

}