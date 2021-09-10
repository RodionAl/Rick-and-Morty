package com.example.rickandmorty.feature.main.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Episode
import com.example.rickandmorty.R

class EpisodeAdapter(
    private val listener: OnLoadNextPageEpisode,
    private val onClick: (Episode) -> Unit
) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    var episodesList = mutableListOf<Episode>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textCodeEpisode: TextView = itemView.findViewById(R.id.textCode)
        val textAirDate: TextView = itemView.findViewById(R.id.textDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_episode, parent, false)
        return EpisodeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.textName.text = episodesList[position].name
        holder.textCodeEpisode.text = episodesList[position].episode
        holder.textAirDate.text = episodesList[position].airDate

        holder.itemView.setOnClickListener { onClick(episodesList[position]) }

        if (position == itemCount - 2) {
            listener.onLoadNextEpisode()
        }
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }

    interface OnLoadNextPageEpisode {
        fun onLoadNextEpisode()
    }
}