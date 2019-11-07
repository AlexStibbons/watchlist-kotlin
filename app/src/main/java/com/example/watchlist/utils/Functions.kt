package com.example.watchlist.utils

import android.util.Log
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.database.models.Movie
import com.example.watchlist.fragments.MoviesFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton




internal fun hasLoginData(email: String?, password: String?): Boolean{
    return if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) true else false
}


// dummy movies
internal  fun initDummies(): List<Movie> {

    val imagepath1 = "https://i0.wp.com/www.tor.com/wp-content/uploads/2019/07/forhecancreep_full.jpg?fit=740%2C777&type=vertical&quality=100&ssl=1"
    val imagepath2 = "https://i1.wp.com/www.tor.com/wp-content/uploads/2019/10/NakedStonedandStabbed_full.jpg?fit=740%2C1028&type=vertical&quality=100&ssl=1"
    val imagepath3 = "https://i2.wp.com/www.tor.com/wp-content/uploads/2019/10/black_cat.jpg?fit=740%2C407&type=vertical&quality=100&ssl=1"
    val imagepath4 = "https://i0.wp.com/www.tor.com/wp-content/uploads/2019/10/Water-a-history.jpg?fit=740%2C1036&type=vertical&quality=100&ssl=1"
    val imagepath5 = "https://i2.wp.com/www.tor.com/wp-content/uploads/2019/09/vetting-final.jpg?fit=740%2C1067&type=vertical&quality=100&ssl=1"

    return listOf(
        Movie(1, "One", "movie one", imagepath1, "tt9595"),
        Movie(568, "Two", "movie two", imagepath2, "tt34"),
        Movie(2, "Three", "movie three", imagepath3, "tt67"),
        Movie(362, "Four", "movie four", imagepath4, "tt89"),
        Movie(869, "Five", "movie five", imagepath5, "tte56"),
        Movie(4, "Six", "movie Six", imagepath1, "tt894"),
        Movie(7, "Seven", "movie Seven", imagepath2, "tt786"),
        Movie(89, "Eight", "movie Eight", imagepath3, "tt7895"),
        Movie(52, "Nine", "movie Nine", imagepath4, "tt5633"),
        Movie(3789, "Ten", "movie Ten", imagepath5, "tt1123"),
        Movie(12, "Eleven", "movie Eleven", imagepath1, "tt5567"),
        Movie(746, "Twelve", "movie Twelve", imagepath2, "tt4566"),
        Movie(999, "Thirteen", "movie Thirteen", imagepath3, "tt986"),
        Movie(204, "Fourteen", "movie Fourteen", imagepath4, "tt9456"),
        Movie(825, "Fifteen", "movie Fifteen", imagepath5, "tt23345")
    )
}