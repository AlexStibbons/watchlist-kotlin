package com.example.watchlist.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    var email: String,

    var password: String
){
    override fun toString(): String = "email: $email"
}