package com.example.testclimatesmart.data.remote

import com.example.testclimatesmart.api.ApiWheaterClient
import com.example.testclimatesmart.core.Constantes
import com.example.testclimatesmart.data.local.ClimateCity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Con el @Inject se prepara la clase para que pueda ser inyectar y ella pueda ser in
 */
class RemoteClimateDayCelsiusDataSource @Inject constructor(private val apiWheaterClient: ApiWheaterClient) :
    RemoteClimateDayDataSource {


    override suspend fun getForecastLastFiveDays(
        lat: Double,
        lon: Double,
        units: String
    ): ClimateCity {
        return withContext(Dispatchers.IO) {
            apiWheaterClient.getForecastLastFiveDays(
                lat,
                lon,
                Constantes.API_KEY,
                units
            )
        }
    }

}