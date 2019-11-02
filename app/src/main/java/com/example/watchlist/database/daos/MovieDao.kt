package com.example.watchlist.database.daos

import androidx.room.*
import com.example.watchlist.database.models.Movie

@Dao
interface MovieDao{

    // LiveData can be used, like so: LiveData<List<Movie>>
    // add `suspend` before fun to make use of coroutines
    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int): Movie

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :title || '%'")
    suspend fun getMoviesByTitle(title: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListMovies(vararg movies: Movie)

    @Delete
    suspend fun removeMovie(movie: Movie)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun removeMovieById(id: Int)
}