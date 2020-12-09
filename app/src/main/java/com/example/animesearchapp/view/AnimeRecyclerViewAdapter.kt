package com.example.animesearchapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animesearchapp.R
import com.example.animesearchapp.model.AnimeSearchResponse

class AnimeRecyclerViewAdapter(
    private var data: AnimeSearchResponse
) : RecyclerView.Adapter<AnimeViewHolder>() {
    fun updateData(data: AnimeSearchResponse) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AnimeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.anime_view_holder,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.onBind(data.results[position])
    }

    override fun getItemCount() = data.results.size
}