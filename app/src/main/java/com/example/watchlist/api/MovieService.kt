package com.example.watchlist.api

import com.example.watchlist.database.models.*
import com.example.watchlist.utils.API_KEY
import retrofit2.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.Appendable

interface MovieService {

    @GET("movie/top_rated")
    suspend fun getTopMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieListResponse>

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("sort_by") sort: String = "popularity.desc",
        @Query("include_adult") isAdult: Boolean = false,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieListResponse>

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<Movie>

}
