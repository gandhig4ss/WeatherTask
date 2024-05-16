package com.example.myapplication.model

data class Main(
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var pressure: Int? = null,
    var seaLevel: Int? = null,
    var grndLevel: Int? = null,
    var humidity: Int? = null,
    var tempKf: Double? = null
)
