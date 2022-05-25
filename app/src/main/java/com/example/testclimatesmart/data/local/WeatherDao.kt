package com.example.testclimatesmart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savWeather(weatherEntity: WeatherEntity): Long

    @Query("SELECT * FROM WeatherEntity")
    suspend fun getWheater(): List<WeatherEntity>;

    @Query("SELECT * FROM WeatherEntity WHERE dt = :dt")
    suspend fun getWeathersByDt(dt: Long): List<WeatherEntity>

}