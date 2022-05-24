package com.example.testclimatesmart.data.local

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class MainEntity (
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
    var humidity: Double,
    @ColumnInfo(name = "temp_kf")
    var temp_kf: Double
    )

