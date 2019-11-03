package com.example.watchlist.api

import com.example.watchlist.database.models.*
import retrofit2.*
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/top_rated")
    suspend fun getTopMovies(@Query("api_key") apiKey: String): Response<MovieListResponse>
}
