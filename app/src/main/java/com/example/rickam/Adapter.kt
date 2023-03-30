package com.example.rickam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationAdapter(private val locations:
          List<Location>) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
    // ViewHolder sınıfını oluştur
    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        val dimensionTextView: TextView = itemView.findViewById(R.id.dimensionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]

        holder.nameTextView.text = location.name
        holder.typeTextView.text = "Type: ${location.type}"
        holder.dimensionTextView.text = "Dimension: ${location.dimension}"
    }

    override fun getItemCount() = locations.size

}