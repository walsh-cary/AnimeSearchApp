package com.example.animesearchapp.model

data class AnimeSearchResponse (
    val results: List<AnimeItem>
)

data class AnimeItem (
    val image_url: String,
    val title: String,
    val synopsis: String,
    val type: String,
    val episodes: Int,
    val score: Float,
    val rated: String
)