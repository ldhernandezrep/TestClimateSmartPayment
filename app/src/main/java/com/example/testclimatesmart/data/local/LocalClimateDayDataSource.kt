package com.example.testclimatesmart.data.local

import javax.inject.Inject

class LocalClimateDayDataSource @Inject constructor(private val climateDao: DayClimateDao) {

    suspend fun saveDayClimate(dayClimateEntity: DayClimateEntity): Long =
        climateDao.saveDayClimate(dayClimateEntity)

    suspend fun getDayClimateLastFive(): List<DayClimateEntity> = climateDao.getDayClimateLastFive()

    suspend fun getDayClimateByDt(dt: Long): DayClimateEntity = climateDao.getDayClimateByDt(dt)

}