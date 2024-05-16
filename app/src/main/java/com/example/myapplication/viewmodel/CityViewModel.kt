package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.MainModel
import com.example.myapplication.model.WeatherData
import com.example.myapplication.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private lateinit var cityName: String
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var units: String

    fun setCityName(cityName: String, latitude: Double, longitude: Double, units: String) {
        this.cityName = cityName
        this.latitude = latitude
        this.longitude = longitude
        this.units = units
    }

    fun getCityWeather(callback: (MainModel) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            val weatherData = weatherRepository.getWeatherForCity(cityName, latitude,longitude,units)
            callback(weatherData)
        }
    }
}