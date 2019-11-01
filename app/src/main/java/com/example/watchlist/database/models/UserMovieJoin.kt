package com.example.watchlist.database.models

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "user_movie",
    primaryKeys = arrayOf("userId", "movieId"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("userId")
        ),
        ForeignKey(
            entity = Movie::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("movieId")
        )
    )
)
data class UserMovieJoin(
    val userId: Int,
    val movieId: Int
)