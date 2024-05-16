package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.ForecastAdapter
import com.example.myapplication.repository.WeatherRepository
import com.example.myapplication.viewmodel.CityViewModel
import com.example.myapplication.viewmodel.CityViewModelFactory

class CityFragment : Fragment() {
    private lateinit var viewModel: CityViewModel

    private lateinit var recycler_view_forecast: RecyclerView
    private lateinit var forecastAdapter: ForecastAdapter
    private lateinit var cityName: String
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        cityName = arguments?.getString("location") ?: ""
        latitude = arguments?.getDouble("latitude") ?: 0.0
        longitude = arguments?.getDouble("longitude") ?: 0.0


        val repository = WeatherRepository("fae7190d7e6433ec3a45285ffcf55c86")
        viewModel =
            ViewModelProvider(this, CityViewModelFactory(repository)).get(CityViewModel::class.java)

        viewModel.setCityName(cityName, latitude, longitude, "metric")

        viewModel.getCityWeather { weatherData ->
            recycler_view_forecast = view.findViewById(R.id.recycler_view_forecast)
            forecastAdapter =
                ForecastAdapter(weatherData)
            recycler_view_forecast.layoutManager = LinearLayoutManager(requireContext())
            recycler_view_forecast.adapter = forecastAdapter
        }

    }


}
