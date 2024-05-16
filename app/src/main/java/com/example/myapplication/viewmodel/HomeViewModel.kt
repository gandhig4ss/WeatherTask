package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Location
import com.example.myapplication.repository.LocationRepository

class HomeViewModel (private val locationRepository: LocationRepository): ViewModel() {

    private val bookmarkedLocations = mutableListOf<String>()

    fun getBookmarkedLocations(): List<String> {
        return bookmarkedLocations
    }

    fun addLocation(location: String) {
        bookmarkedLocations.add(location)
    }

    fun removeLocation(location: String) {
        bookmarkedLocations.remove(location)
    }

    fun searchLocation(query: String): List<Location> {
        return locationRepository.searchLocation(query)
    }


}