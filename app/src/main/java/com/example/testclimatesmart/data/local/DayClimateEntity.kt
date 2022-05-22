package com.example.testclimatesmart.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testclimatesmart.data.DayClimate

@Entity
class DayClimateEntity(
    @PrimaryKey val dt: Long = 0,
    @ColumnInfo(name = "visibility")
    var visibility: Int = 0,
    @ColumnInfo(name = "pop")
    var pop: Double = 0.0,
    @ColumnInfo(name = "dt_txt")
    var dt_txt: String = "",
    @ColumnInfo(name = "dt_txt_short")
    var dt_txt_short: String = "",
)

fun DayClimate.toDayClimateEntity(dt_txt_short: String): DayClimateEntity = DayClimateEntity(
    this.dt,
    this.visibility,
    this.pop,
    this.dt_txt,
    dt_txt_short,
)