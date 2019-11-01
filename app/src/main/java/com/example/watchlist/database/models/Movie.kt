package com.example.watchlist.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(

    @PrimaryKey
    val id: Int,

    val title: String,

    val overview: String,

    val poster_path: String,

    val imdb_id: String?

){
    override fun toString(): String = " $title "
}