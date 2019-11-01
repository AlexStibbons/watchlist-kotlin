package com.example.watchlist.utils


internal fun hasLoginData(email: String?, password: String?): Boolean{
    return if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) true else false
}

internal fun isFave(movieId: Int, movieIds: List<Int>): Boolean {
    return when (movieId) {
        in movieIds -> true
        else -> false
    }
}