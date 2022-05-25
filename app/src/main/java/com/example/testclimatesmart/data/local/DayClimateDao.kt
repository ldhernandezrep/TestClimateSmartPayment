package com.example.testclimatesmart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DayClimateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDayClimate(dayClimateEntity: DayClimateEntity): Long

    @Query("SELECT dt,visibility,pop,dt_txt,dt_txt_short FROM DayClimateEntity")
    suspend fun getDayClimateLastFive(): List<DayClimateEntity>;

    @Query("SELECT dt,visibility,pop,dt_txt,dt_txt_short FROM DayClimateEntity WHERE dt = :dt")
    suspend fun getDayClimateByDt(dt: Long): DayClimateEntity;

}