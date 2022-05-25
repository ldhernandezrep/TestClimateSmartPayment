package com.example.testclimatesmart.data

import com.example.testclimatesmart.data.local.MainEntity
import com.example.testclimatesmart.data.local.WeatherEntity

data class MainClimate(
    var dt: Long = 0,
    var temp: Double = 0.0,
    var feels_like: Double = 0.0,
    var temp_min: Double = 0.0,
    var temp_max: Double = 0.0,
    var pressure: Int = 0,
    var humidity: Int = 0,
    var temp_kf: Double = 0.0
)

data class MainList(val results: List<MainClimate> = listOf())

fun MainEntity.toMian(): MainClimate = MainClimate(
    this.dt,
    this.tmep,
    this.feels_like,
    this.temp_min,
    this.temp_max,
    this.pressure,
    this.humidity,
    this.temp_kf,
)


fun List<MainEntity>.toListMain(): MainList {
    val resultList = mutableListOf<MainClimate>()
    this.forEach {
        resultList.add(it.toMian())
    }

    return MainList(resultList)
}


