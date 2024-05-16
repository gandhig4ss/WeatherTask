package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.model.MainModel

class WeatherRepository(private val apiKey: String) {
    private val apiService = RetrofitClient.instance

    suspend fun getWeatherForCity(
        cityName: String,
        latitude: Double,
        longitude: Double,
        units: String
    ): MainModel {
        //val response = apiService.getWeather(cityName, apiKey)
        val response = apiService.getForecast(latitude, longitude, apiKey, units)
        return MainModel(
            cod = response.cod,
            message = response.message,
            cnt = response.cnt,
            list = response.list
        )
    }
}