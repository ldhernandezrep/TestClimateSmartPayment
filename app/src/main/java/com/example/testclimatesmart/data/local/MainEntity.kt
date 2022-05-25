package com.example.testclimatesmart.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testclimatesmart.data.MainClimate
import com.example.testclimatesmart.data.Weather

@Entity
class MainEntity(
    @PrimaryKey var dt: Long,
    @ColumnInfo(name = "temp")
    var tmep: Double,
    @ColumnInfo(name = "feels_like")
    var feels_like: Double,
    @ColumnInfo(name = "temp_min")
    var temp_min: Double,
    @ColumnInfo(name = "temp_max")
    var temp_max: Double,
    @ColumnInfo(name = "pressure")
    var pressure: Int,
    @ColumnInfo(name = "humidity")
    var humidity: Int,
    @ColumnInfo(name = "temp_kf")
    var temp_kf: Double
)


fun MainClimate.toMainEntity(dt: Long): MainEntity = MainEntity(
    dt,
    this.temp,
    this.feels_like,
    this.temp_min,
    this.temp_max,
    this.pressure,
    this.humidity,
    this.temp_kf
)
