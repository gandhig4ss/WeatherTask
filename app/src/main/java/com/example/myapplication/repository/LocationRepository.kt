package com.example.myapplication.repository

import android.location.Geocoder
import com.example.myapplication.model.Location
import java.io.IOException

class LocationRepository(private val geocoder: Geocoder) {

    fun searchLocation(query: String): List<Location> {
        val results = mutableListOf<Location>()
        try {
            val addresses = geocoder.getFromLocationName(query, 10)
            for (address in addresses!!) {
                val location = Location(address.latitude, address.longitude, address.locality)
                results.add(location)
            }
        } catch (e: IOException) {
        }
        return results
    }
}