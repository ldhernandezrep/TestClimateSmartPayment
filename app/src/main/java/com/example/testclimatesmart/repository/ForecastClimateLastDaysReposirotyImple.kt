package com.example.testclimatesmart.repository

import com.example.testclimatesmart.core.ConnectionInternet
import com.example.testclimatesmart.data.*
import com.example.testclimatesmart.data.local.LocalClimateDayDataSource
import com.example.testclimatesmart.data.local.LocalWeatherDataSource
import com.example.testclimatesmart.data.local.toDayClimateEntity
import com.example.testclimatesmart.data.local.toWeatherEntity
import javax.inject.Inject

class ForecastClimateLastDaysReposirotyImple @Inject constructor(
    private val remoteClimateDayDataSource: RemoteClimateDayDataSource,
    private val localClimateDayDataSource: LocalClimateDayDataSource,
    private val localWeatherDataSource: LocalWeatherDataSource
) :
    ForecastClimateLastDaysReposiroty {


    override suspend fun getClimateLastDays(
        lat: Double,
        long: Double,
        units: String
    ): List<DayClimate> {
        var listDayReturn: List<DayClimate> = listOf()
        var listWeather: List<Weather> = listOf()

        //Si hay conexion a internet disponible traer los nuevos datos si no traer los de la base
        if (ConnectionInternet.isInternetAvailable()) {
            val climateCity = remoteClimateDayDataSource.getForecastLastFiveDays(lat, long, units)
            climateCity.list.forEach({
                var dt: Long = it.dt
                localClimateDayDataSource.saveDayClimate(
                    it.toDayClimateEntity(it.dt_txt.substring(0, 10))
                )
                it.weather.forEach({
                    localWeatherDataSource.saveWeather(it.toWeatherEntity(dt))
                })
            })

            listDayReturn =
                localClimateDayDataSource.getDayClimateLastFive().toListDayClimate(
                    localWeatherDataSource.getWeathers().toListWeather().results
                ).results

        } else {
            listDayReturn =
                localClimateDayDataSource.getDayClimateLastFive().toListDayClimate(
                    localWeatherDataSource.getWeathers().toListWeather().results
                ).results
        }
        return listDayReturn

    }


}