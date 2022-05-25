package com.example.testclimatesmart.data.remote

import com.example.testclimatesmart.data.remote.Coord

data class City(
    var id: Int = 0,
    var name: String = "",
    var coords: Coord = Coord(),
    var country: String = "",
    var population: Int = 0,
    var timezone: String = ""
)

