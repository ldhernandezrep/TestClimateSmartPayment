package com.example.testclimatesmart.data

import com.example.testclimatesmart.data.local.DayClimateEntity

data class DayClimate(
    var dt: Long = 0,
    var main: MainClimate = MainClimate(0.0, 0.0, 0.0, 0.0, 0.0, 0, 0.0),
    var weather: List<Weather> = listOf(),
    var wind: Wind = Wind(0.0, 0, 0.0),
    var visibility: Int = 0,
    var pop: Double = 0.0,
    var dt_txt: String = "",
    var dt_txt_short: String = ""
)

data class DayClimateList(val results: List<DayClimate> = listOf())

fun DayClimateEntity.toDayClimateDay(weathers: List<Weather>): DayClimate = DayClimate(
    this.dt,
    MainClimate(0.0, 0.0, 0.0, 0.0, 0.0, 0, 0.0),
    weathers,
    Wind(0.0, 0, 0.0),
    this.visibility,
    this.pop,
    this.dt_txt,
    this.dt_txt_short
)

fun List<DayClimateEntity>.toListDayClimate(weathers: List<Weather>): DayClimateList {
    val resultList = mutableListOf<DayClimate>()
    this.forEach {
        resultList.add(it.toDayClimateDay(weathers))
    }

    return DayClimateList(resultList)
}
