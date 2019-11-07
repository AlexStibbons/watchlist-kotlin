package com.example.watchlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.watchlist.R
import com.example.watchlist.database.models.*
import com.example.watchlist.utils.BASE_IMG_URL
import com.example.watchlist.utils.initDummies
import kotlinx.android.synthetic.main.layout_movie_item.view.*
import android.widget.CompoundButton
import com.example.watchlist.api.RetrofitFactory


class MoviesRecyclerViewAdapter(
    var movies: MutableList<Movie>,
    var watchlistIds: MutableList<Int>,
    val context: Context

) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(com.example.watchlist.R.layout.layout_movie_item,
                parent,false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)
    }

    fun setWatchilistIds(userWatchlist: List<Int>) {
        watchlistIds.clear()
        watchlistIds.addAll(userWatchlist)
        notifyDataSetChanged()
    }

    fun addMovies(moreMovies: List<Movie>?) {
        movies.addAll(moreMovies!!)
        notifyDataSetChanged()
    }

    // what is in one movie item?
  inner  class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image = view.movie_image
        val title = view.movie_title
        val description  = view.movie_description
        val btnFave = view.btn_favorite
        val movieItemLayout = view.layout_movie_item
        var isMovieFave: Boolean = false


        fun bind(position: Int) {
            btnFave.setOnCheckedChangeListener(null)
            if (isFave(movies[position].id)) {
                btnFave.isChecked = true
                isMovieFave = true
            }

            title.text = movies[position].title
            description.text = movies[position].overview
            movieItemLayout.setOnClickListener(View.OnClickListener {
                // openMovie(movies.get(position).id, holder.isMovieFave)
                Toast.makeText(context, "Clicked ${movies.get(position).title}", Toast.LENGTH_LONG).show()
            })

           btnFave.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
               if (isChecked) {
                   // add to local db
                   Toast.makeText(context, "Checked! ${movies.get(position).title}", Toast.LENGTH_LONG).show()
               } else {
                   // remove from local db
                   Toast.makeText(context, "Unchecked! ${movies.get(position).title}", Toast.LENGTH_LONG).show()
               }
           })

            Glide.with(context)
                .asBitmap()
                .load(BASE_IMG_URL + movies.get(position).poster_path)
                .into(image)

        }
    }

    fun isFave(movieId: Int): Boolean {
        return when (movieId) {
            in watchlistIds -> true
            else -> false
        }
    }


}