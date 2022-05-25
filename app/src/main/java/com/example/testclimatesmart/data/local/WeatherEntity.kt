package com.example.testclimatesmart.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testclimatesmart.data.DayClimate
import com.example.testclimatesmart.data.Weather

@Entity
class WeatherEntity(
    @PrimaryKey var dt: Long,
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "main")
    var main: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "icon")
    var icon: String
)

fun Weather.toWeatherEntity(dt: Long): WeatherEntity = WeatherEntity(
    dt,
    this.id,
    this.main,
    this.description,
    this.icon
)