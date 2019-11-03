package com.example.watchlist.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.adapters.MoviesRecyclerViewAdapter
import com.example.watchlist.api.MovieService
import com.example.watchlist.api.RetrofitFactory
import com.example.watchlist.database.LocalDb
import com.example.watchlist.database.models.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.AbsListView
import androidx.core.view.get

private const val USER_ID = "userId"

class MoviesFragment : Fragment() {

    private val TAG = MoviesFragment::class.java.simpleName

    // variables needed
    private var userId: Int? = null
    var allMovies: MutableList<Movie> = ArrayList<Movie>()
    var dummyIds: MutableList<Int> = arrayListOf(1, 2, 4)

    // recycler view
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MoviesRecyclerViewAdapter

    // retrofit & db
    var localDb: LocalDb? = null
    lateinit var movieService: MovieService
    lateinit var fab: FloatingActionButton

    // scrolling and pagination
    var currentPage: Int = 1
    var isScrolling: Boolean = false
    var currentItems: Int = 0
    var totalItems:Int = 0
    var scrollOutItems:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getInt(USER_ID)
            Log.e("$TAG onCREATE", "user id is $userId")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("onCreateVIEW", "user id is $userId")
        super.onCreateView(inflater, container, savedInstanceState)

        localDb = activity?.let { LocalDb.getInstance(it) }
        movieService = RetrofitFactory.makeMovieService()

        val rootView: View = inflater.inflate(com.example.watchlist.R.layout.fragment_movie_list, container, false)
        fab = rootView.findViewById(com.example.watchlist.R.id.fab)

        val testy: TextView = rootView.findViewById(com.example.watchlist.R.id.idView)
        testy.setText("User id is $userId")

        recyclerView = rootView.findViewById(com.example.watchlist.R.id.movie_list_recycler_view)
        adapter = MoviesRecyclerViewAdapter(allMovies, dummyIds, this.activity!!)

        fab.setOnClickListener(View.OnClickListener {
            recyclerView.smoothScrollToPosition(0)
        })

        fetchMovies(movieService)

        return rootView
    }

    // static function constructor
    companion object {
        fun newInstance(userId: Int): MoviesFragment {
            return MoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                }
            }
        }
    }

    fun fetchMovies(movieService: MovieService) {

        getMovies(currentPage, movieService) // get from api
        getWatchlistIds(userId!!) // get watchlist
        initRecyclerView()
    }

    fun getMovies(currentPage: Int, movieService: MovieService) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = movieService.getPopularMovies(page = currentPage).body()?.results
            withContext(Dispatchers.Main) {
                adapter.addMovies(movies)
            }
        }
    }

    fun getWatchlistIds(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val idList = localDb?.userMovieDao()?.getMovieIdsForUser(userId)

            withContext(Dispatchers.Main) {
                //adapter.setWatchilistIds(idList!!)
                adapter.setWatchilistIds(arrayListOf(475557, 290859, 920, 338967))
            }
        }
    }

    fun initRecyclerView(){

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.let {
            it.adapter = adapter
            it.layoutManager = layoutManager
        }
        addOnScroll(recyclerView, layoutManager)

    }

    fun addOnScroll(recyclerView: RecyclerView, layoutManager: LinearLayoutManager) {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL ) {
                    isScrolling = true
                    fab.hide()
                }
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    fab.show()
                }
            }


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currentItems = layoutManager.childCount
                totalItems = layoutManager.itemCount
                scrollOutItems = layoutManager.findFirstVisibleItemPosition()

                if (isScrolling && currentItems + scrollOutItems === totalItems) {
                    isScrolling = false
                    currentPage++
                    Log.d("OnScrolled", "Current Page:$currentPage")
                    getMovies(currentPage, movieService)
                }
            }
        })
    }
}