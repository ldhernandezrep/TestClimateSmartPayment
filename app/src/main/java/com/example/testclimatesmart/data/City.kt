package com.example.testclimatesmart.data

data class City(
    var id: Int = 0,
    var name: String = "",
    var coords: Coord = Coord(),
    var country: String = "",
    var population: Int = 0,
    var timezone: String = ""
)

