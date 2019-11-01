package com.example.watchlist.database.daos

import androidx.room.*
import com.example.watchlist.database.models.Movie

@Dao
interface MovieDao{

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE movie.id = :id")
    fun getMovieById(id: Int): Movie

    @Query("SELECT * FROM movie WHERE movie.title LIKE '%' || :title || '%'")
    fun getMoviesByTitle(title: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addListMovies(vararg movies: Movie)
}