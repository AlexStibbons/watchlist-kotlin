package com.example.watchlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.watchlist.R
import com.example.watchlist.api.RetrofitFactory
import com.example.watchlist.utils.EXTRA_USER_ID
import com.example.watchlist.database.models.*
import com.example.watchlist.utils.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Testy : AppCompatActivity() {

    private var id: Int = -3
    lateinit var textId: TextView
    lateinit var moviesText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testy)

        moviesText = findViewById(R.id.rf_text)

        id = intent.getIntExtra(EXTRA_USER_ID, -1)
        moviesText.setText("User Id is $id\n\n")

        getMovies()
    }


    private fun getMovies() {
        // first, get service
        val movieService = RetrofitFactory.makeMovieService()
        // then, go to network
        CoroutineScope(Dispatchers.IO).launch {
            val response: List<Movie>? = movieService.getTopMovies(API_KEY).body()?.results
            // then, go back to main
            withContext(Dispatchers.Main) {

                populateView(response)
            }
        }
    }

    private fun populateView(movies: List<Movie>?) {
        if (movies != null) {
            for (movie in movies) {
                var content: String =  "Title: ${movie.title} \n" +
                        "Description: ${movie.overview}\n" +
                        "Imdb: ${movie.imdb_id}\n" +
                        "Poster: ${movie.poster_path}\n\n"

                moviesText.append(content)
            }
        }
    }
}
