package com.example.testclimatesmart.data

data class ClimateCity(
    var cod: String = "",
    var message: String = "",
    var cnt: Int = 0,
    var list: List<DayClimate> = listOf(),
    var city: City = City()
)
