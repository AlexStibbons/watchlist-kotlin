package com.example.watchlist.database.daos

import androidx.room.*
import com.example.watchlist.database.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT user.id FROM user WHERE user.email = :email")
    fun getUserIdByEmail(email: String): Int // could this work?

    @Query("SELECT * FROM user WHERE user.id = :id")
    fun getUserById(id: Int): User

    @Insert
    fun addUser(user: User): Long // insert always returns long

    @Delete
    fun removeUser(user: User)

    @Update
    fun updateUser(user: User)
}