package com.example.testclimatesmart.repository

import com.example.testclimatesmart.core.ConnectionInternet
import com.example.testclimatesmart.core.Constantes
import com.example.testclimatesmart.data.*
import com.example.testclimatesmart.data.local.*
import com.example.testclimatesmart.data.remote.RemoteClimateDayDataSource
import javax.inject.Inject

class ForecastClimateLastDaysReposirotyImple @Inject constructor(
    private val remoteClimateDayDataSource: RemoteClimateDayDataSource,
    private val localClimateDayDataSource: LocalClimateDayDataSource,
    private val localWeatherDataSource: LocalWeatherDataSource,
    private val localMainClimateDataSource: LocalMainClimateDataSource,
) :
    ForecastClimateLastDaysReposiroty {


    override suspend fun getClimateLastDays(
        lat: Double,
        long: Double,
        units: String
    ): List<DayClimate> {
        var listDayReturn: List<DayClimate> = listOf()

        //Si hay conexion a internet disponible traer los nuevos datos si no traer los de la base
        if (ConnectionInternet.isInternetAvailable()) {
            val climateCity = remoteClimateDayDataSource.getForecastLastFiveDays(lat, long, units)
            climateCity.list.forEach({
                localClimateDayDataSource.saveDayClimate(
                    it.toDayClimateEntity(
                        it.dt_txt.substring(
                            0,
                            10
                        )
                    )
                )
                localMainClimateDataSource.saveDayClimate(it.main.toMainEntity(it.dt))

                it.weather.forEach({ itweather ->
                    localWeatherDataSource.saveWeather(itweather.toWeatherEntity(it.dt))
                })


            })

            listDayReturn =
                localClimateDayDataSource.getDayClimateLastFive().toListDayClimate(
                    localWeatherDataSource.getWeathers().toListWeather().results,
                    localMainClimateDataSource.getMainClimateList().toListMain().results
                ).results

        } else {
            listDayReturn =
                localClimateDayDataSource.getDayClimateLastFive().toListDayClimate(
                    localWeatherDataSource.getWeathers().toListWeather().results,
                    localMainClimateDataSource.getMainClimateList().toListMain().results
                ).results
        }
        return listDayReturn

    }

    override suspend fun getClimateDayByDt(dt: Long): DayClimate {
        var listDayReturn: DayClimate? = null

        listDayReturn =
            localClimateDayDataSource.getDayClimateByDt(dt).toDayClimateDay(
                localWeatherDataSource.getWeathersByDt(dt).toListWeather().results,
                localMainClimateDataSource.getMainClimateByDt(dt).toMian()
            )

        return listDayReturn

    }


}