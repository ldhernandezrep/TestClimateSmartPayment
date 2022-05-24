package com.example.testclimatesmart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MainClimateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMainClimate(mainEntity: MainEntity): Long

    @Query("SELECT dt,tmep,feels_like,temp_min,temp_max,pressure,humidity,temp__kf FROM MainEntity")
    suspend fun getListClimateMain(): List<MainEntity>;

}