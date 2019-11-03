package com.example.watchlist.database.models

data class MovieListResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>)