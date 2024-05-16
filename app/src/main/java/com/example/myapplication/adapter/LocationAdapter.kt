package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Location
import com.example.other.LocationDiffCallback

class LocationAdapter(private val onItemClick: ((Location) -> Unit)? = null) :
    ListAdapter<Location, LocationAdapter.LocationViewHolder>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
    }


    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textLocationName: TextView = itemView.findViewById(R.id.text_location_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(getItem(position))

                }
            }
        }

        fun bind(location: Location) {
            textLocationName.text = location.name
        }
    }
}