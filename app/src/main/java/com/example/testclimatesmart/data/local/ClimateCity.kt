package com.example.testclimatesmart.data.local

import com.example.testclimatesmart.data.remote.City
import com.example.testclimatesmart.data.DayClimate

data class ClimateCity(
    var cod: String = "",
    var message: String = "",
    var cnt: Int = 0,
    var list: List<DayClimate> = listOf(),
    var city: City = City()
)
