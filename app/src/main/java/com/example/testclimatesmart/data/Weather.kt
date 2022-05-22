package com.example.testclimatesmart.data

import com.example.testclimatesmart.data.local.DayClimateEntity
import com.example.testclimatesmart.data.local.WeatherEntity

data class Weather(
    var id: Int = 0,
    var main: String = "",
    var description: String = "",
    var icon: String = "",
    var dt: Long = 1
)

data class WeatherList(val results: List<Weather> = listOf())

fun WeatherEntity.toWeather(): Weather = Weather(
    this.id,
    this.main,
    this.description,
    this.icon,
    this.dt
)


fun List<WeatherEntity>.toListWeather(): WeatherList {
    val resultList = mutableListOf<Weather>()
    this.forEach {
        resultList.add(it.toWeather())
    }

    return WeatherList(resultList)
}
