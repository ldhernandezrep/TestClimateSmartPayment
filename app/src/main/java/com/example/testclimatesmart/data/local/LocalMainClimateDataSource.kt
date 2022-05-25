package com.example.testclimatesmart.data.local

import javax.inject.Inject

class LocalMainClimateDataSource @Inject constructor(private val mainClimateDao: MainClimateDao) {

    suspend fun saveDayClimate(mainEntity: MainEntity): Long =
        mainClimateDao.saveMainClimate(mainEntity)

    suspend fun getMainClimateList(): List<MainEntity> =
        mainClimateDao.getListClimateMain()

    suspend fun getMainClimateByDt(dt: Long): MainEntity = mainClimateDao.getMainClimateByDt(dt)
}