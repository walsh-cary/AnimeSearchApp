package com.example.animesearchapp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animesearchapp.R
import com.example.animesearchapp.model.AnimeItem
import kotlinx.android.synthetic.main.anime_view_holder.view.*
import kotlin.math.roundToLong

class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Bind response data to views
    fun onBind(animeItem: AnimeItem) {
        itemView.animeTitleTextView.text = animeItem.title
        Glide.with(itemView).load(animeItem.image_url).into(itemView.animeImageView)
        itemView.animeSynopsisTextView.text = animeItem.synopsis
        itemView.animeTypeTextView.text = itemView.context.getString(R.string.type, animeItem.type)
        itemView.animeEpisodesTextView.text = itemView.context.getString(R.string.episode_count, animeItem.episodes)
        itemView.animeScoreTextView.text = itemView.context.getString(R.string.score, animeItem.score)
        itemView.animeRatedTextView.text = itemView.context.getString(R.string.rated, animeItem.rated)
    }
}