package com.example.animesearchapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animesearchapp.R
import com.example.animesearchapp.model.AnimeSearchResponse
import com.example.animesearchapp.viewmodel.AnimeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    // ViewModel is initialized when needed
    lateinit var animeViewModel: AnimeViewModel
    lateinit var animeRecyclerViewAdapter: AnimeRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create ViewModelProvider instance
        animeViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return AnimeViewModel() as T
                }
            }
        ).get(AnimeViewModel::class.java)

        // Perform network call
        animeViewModel.initRetrofit(getString(R.string.anime_title))

        // Initialize adapter with empty data while waiting for network call
        animeRecyclerViewAdapter = AnimeRecyclerViewAdapter(AnimeSearchResponse(listOf()))

        // Set up LiveData observer
        animeViewModel.animeDataSet.observe(this, Observer {
            animeRecyclerViewAdapter.updateData(it)
        })

        animeRecyclerView.layoutManager = LinearLayoutManager(this)
        animeRecyclerView.adapter = animeRecyclerViewAdapter

        // Set listener for user to search for different strings
        animeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                animeViewModel.initRetrofit(query!!.toLowerCase(Locale.ROOT))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}