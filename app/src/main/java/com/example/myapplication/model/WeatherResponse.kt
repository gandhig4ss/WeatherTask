package com.example.myapplication.model

data class WeatherResponse(
    val main: Main,
    val wind: Wind,
    val rain: Rain?,
)



data class Wind(
    val speed: Double
)

data class Rain(
    val `3h`: Double
)
