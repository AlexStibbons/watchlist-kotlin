package com.example.watchlist.api

import com.example.watchlist.utils.BASE_MOVIEDB_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

// unsure about this at all

object RetrofitFactory {

    fun makeMovieService(): MovieService {
        return Retrofit.Builder()
            .baseUrl(BASE_MOVIEDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieService::class.java)
    }
}