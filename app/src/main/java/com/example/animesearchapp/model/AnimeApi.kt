package com.example.animesearchapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    @GET("v3/search/anime?")
    fun getAnime(@Query("q") q: String): Call<AnimeSearchResponse>
}