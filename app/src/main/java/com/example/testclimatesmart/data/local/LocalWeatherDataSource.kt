package com.example.testclimatesmart.data.local

import javax.inject.Inject

class LocalWeatherDataSource @Inject constructor(private val weatherDao: WeatherDao) {

    suspend fun saveWeather(weatherEntity: WeatherEntity): Long =
        weatherDao.savWeather(weatherEntity)

    suspend fun getWeathers(): List<WeatherEntity> = weatherDao.getWheater()
    suspend fun getWeathersByDt(dt: Long): List<WeatherEntity> = weatherDao.getWeathersByDt(dt)

}