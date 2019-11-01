package com.example.watchlist.database.daos

import androidx.room.*
import com.example.watchlist.database.models.*

@Dao
interface UserMovieJoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUserMovie(userMovie: UserMovieJoin)

    @Query("DELETE FROM user_movie WHERE user_movie.userId = :userId AND user_movie.movieId = :movieId")
    fun removeUserMovieByIds(userId: Int, movieId: Int)

    @Delete
    fun removeUserMovie(userMovie: UserMovieJoin)

    @Query("SELECT * FROM movie INNER JOIN user_movie ON movie.id = user_movie.movieId WHERE user_movie.userId = :userId")
    fun getMoviesForUser(userId: Int): List<Movie>

    @Query("SELECT movie.id FROM movie INNER JOIN user_movie ON movie.id = user_movie.movieId WHERE user_movie.userId = :userId")
    fun getMovieIdsForUser(userId: Int): List<Int>

    @Query("SELECT * FROM user INNER JOIN user_movie ON user.id = user_movie.userId WHERE user_movie.movieId = :movieId")
    fun getUsersForMovie(movieId: Int): List<User>

}