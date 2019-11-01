package com.example.watchlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.watchlist.database.daos.*
import com.example.watchlist.database.models.*
import com.example.watchlist.utils.DB_NAME

@Database(
    entities = arrayOf(User::class, Movie::class, UserMovieJoin::class),
    version = 1

)
abstract class LocalDb : RoomDatabase() {

    // getting all daos
    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
    abstract fun userMovieDao(): UserMovieJoinDao

    // now an instance/singleton
    companion object {

        // declare instance
        @Volatile // what is this?
        private var instance: LocalDb? = null

        // more info on creating singletons in kotlin
        fun getInstance(context: Context): LocalDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LocalDb {
            return Room.databaseBuilder(context, LocalDb::class.java, DB_NAME).build()
        }

    }

}

/*        fun getLocalDb(context: Context): LocalDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDb::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }*/