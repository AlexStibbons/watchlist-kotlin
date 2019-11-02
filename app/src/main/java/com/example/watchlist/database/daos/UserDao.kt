package com.example.watchlist.database.daos

import androidx.room.*
import com.example.watchlist.database.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT user.id FROM user WHERE user.email = :email")
    suspend fun getUserIdByEmail(email: String): Int

    @Query("SELECT * FROM user WHERE user.id = :id")
    suspend fun getUserById(id: Int): User

    @Insert
    suspend fun addUser(user: User): Long // insert always returns long

    @Delete
    suspend fun removeUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}