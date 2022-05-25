package com.example.testclimatesmart.data.local

import androidx.room.*


@Dao
interface MainClimateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMainClimate(mainEntity: MainEntity): Long

    @Query("SELECT dt,temp,feels_like,temp_min,temp_max,pressure,humidity,temp_kf FROM MainEntity")
    suspend fun getListClimateMain(): List<MainEntity>;

    @Query("SELECT dt,temp,feels_like,temp_min,temp_max,pressure,humidity,temp_kf FROM MainEntity WHERE dt = :dt")
    abstract fun getMainClimateByDt(dt: Long): MainEntity


}