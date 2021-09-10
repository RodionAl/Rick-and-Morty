package com.example.rickandmorty.feature.main.location

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Location
import com.example.rickandmorty.R

class LocationAdapter(
    private val listener: OnLoadNextPageLocation,
    private val onClick: (Location) -> Unit
) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    var locationsList = mutableListOf<Location>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textType: TextView = itemView.findViewById(R.id.textType)
        val textDimension: TextView = itemView.findViewById(R.id.textDimension)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.textName.text = locationsList[position].name
        holder.textType.text = locationsList[position].type
        holder.textDimension.text = locationsList[position].dimension

        holder.itemView.setOnClickListener { onClick(locationsList[position]) }

        if (position == itemCount - 2) {
            listener.onLoadNextLocation()
        }
    }

    override fun getItemCount(): Int {
        return locationsList.size
    }

    interface OnLoadNextPageLocation {
        fun onLoadNextLocation()
    }
}