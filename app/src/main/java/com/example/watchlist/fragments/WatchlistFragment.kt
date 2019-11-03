package com.example.watchlist.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.watchlist.R
import com.example.watchlist.database.models.User


private const val USER_ID = "userId"

class WatchlistFragment : Fragment() {

    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getInt(USER_ID)
            Log.e("onCREATE", "user id is $userId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e("onCreateVIEW", "user id is $userId")
        return null
    }


    companion object {
        fun newInstance(userId: Int): WatchlistFragment{
            return WatchlistFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                }
            }
        }
    }

    /*public static FaveMoviesFragment getInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("userId", id);

        FaveMoviesFragment faveMoviesFragment = new FaveMoviesFragment();
        faveMoviesFragment.setArguments(args);

        return faveMoviesFragment;
    }*/
}
