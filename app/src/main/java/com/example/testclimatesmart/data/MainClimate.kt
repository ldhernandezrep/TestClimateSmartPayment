package com.example.testclimatesmart.data

data class MainClimate(
    var temp: Double = 0.0,
    var feels_like: Double = 0.0,
    var temp_min: Double = 0.0,
    var temp_max: Double = 0.0,
    var pressure: Double = 0.0,
    var humidity: Int = 0,
    var temp_kf: Double = 0.0
)
