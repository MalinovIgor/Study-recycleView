package ru.startandroid.develop.study_recycleview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LocationsAdapter() : RecyclerView.Adapter<LocationViewHolder>(){

    var infos = ArrayList<MovieInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(parent)

    override fun getItemCount(): Int = infos.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(infos.get(position))
    }
}