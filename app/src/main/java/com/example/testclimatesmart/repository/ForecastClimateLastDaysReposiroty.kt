package com.example.testclimatesmart.repository

import com.example.testclimatesmart.data.DayClimate

interface ForecastClimateLastDaysReposiroty {

    suspend fun getClimateLastDays(lat: Double, long: Double, units: String): List<DayClimate>

    suspend fun getClimateDayByDt(dt: Long): DayClimate

}