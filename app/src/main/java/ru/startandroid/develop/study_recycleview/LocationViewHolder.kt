package ru.startandroid.develop.study_recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LocationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.locations, parent, false)
) {
    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)
    var cover : ImageView = itemView.findViewById((R.id.image))


    fun bind(movieInfo: MovieInfo){
        Glide.with(itemView)
            .load(movieInfo.image)
            .into(cover)
        title.text = movieInfo.title
        description.text = movieInfo.description
    }
}