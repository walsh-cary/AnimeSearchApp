package com.example.animesearchapp.model

import com.example.animesearchapp.viewmodel.AnimeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimeNetwork(val viewModel: AnimeViewModel) {
    var interceptor: HttpLoggingInterceptor? = null
    var client: OkHttpClient? = null

    // Perform network call and return results to ViewModel
    fun initSearchRetrofit(
        baseurl: String,
        query: String
    ) {
        interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor!!)
        }.build()

        getApi(baseurl, client!!).getAnime(query)
            .enqueue(
                object : Callback<AnimeSearchResponse> {
                    override fun onResponse(
                        call: Call<AnimeSearchResponse>,
                        response: Response<AnimeSearchResponse>
                    ) {
                        response.body()?.let { viewModel.updateLiveData(it) }
                    }

                    override fun onFailure(call: Call<AnimeSearchResponse>, t: Throwable) {
                        return Unit
                    }
                }
            )
    }

    // Retrieve API instance
    private fun getApi(baseUrl: String, client: OkHttpClient) : AnimeApi =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).client(client)
            .build().create(AnimeApi::class.java)
}