package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.MainModel

class ForecastAdapter(private val forecastList: MainModel) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temp: TextView = itemView.findViewById(R.id.temp)
        val weatherDescription: TextView = itemView.findViewById(R.id.weatherDescription)
        val humidity: TextView = itemView.findViewById(R.id.humidity)
        val pressure: TextView = itemView.findViewById(R.id.pressure)
        val date: TextView = itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_list_item, parent, false)
        return ForecastViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecastList.list[position]
        val strs = forecast.main!!.temp.toString().split(".").toTypedArray()

        holder.temp.text = strs[0]+ " \u2103"
        holder.weatherDescription.text = forecast.weather[0].main
        holder.humidity.text = "Humidity: "+forecast.main!!.humidity.toString()
        holder.pressure.text ="Pressure: "+ forecast.main!!.pressure.toString()
        holder.date.text = forecast.dt_txt
    }

    override fun getItemCount(): Int {
        return forecastList.list.size
    }
}