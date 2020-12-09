package com.example.animesearchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animesearchapp.model.AnimeNetwork
import com.example.animesearchapp.model.AnimeSearchResponse

class AnimeViewModel : ViewModel() {
    // Initialize LiveData object and provide getter
    private val _animeDataSet: MutableLiveData<AnimeSearchResponse> = MutableLiveData()
    val animeDataSet: LiveData<AnimeSearchResponse>
        get() = _animeDataSet

    // Set LiveData object
    fun updateLiveData(dataSet: AnimeSearchResponse) {
        this._animeDataSet.value = dataSet
    }

    // Perform network call
    fun initRetrofit(query: String) {
        val network = AnimeNetwork(this)
        network.initSearchRetrofit("https://api.jikan.moe/", query)
    }
}