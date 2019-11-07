package com.example.watchlist.api

import com.example.watchlist.utils.BASE_MOVIEDB_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


// object{} is simple singleton
// when in class `companion object{}`
object RetrofitFactory {

    // val movie by lazy{
    // code from below
    // }

    fun makeMovieService(): MovieService {
        return Retrofit.Builder()
            .baseUrl(BASE_MOVIEDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieService::class.java)
    }
}

// will need another one for TvService --> how to create
// a movie/tv service instance on an already existing retrofit instance?

// an extenstion function? [companions can have extension functions, but these?]
// a nested object?
// a regular function within the object?

// does each service need its own instance of retrofit or
// can they both use one instance?