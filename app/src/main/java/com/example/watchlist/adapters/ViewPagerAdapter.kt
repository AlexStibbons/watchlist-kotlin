package com.example.watchlist.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.watchlist.fragments.MoviesFragment
import com.example.watchlist.fragments.WatchlistFragment

class ViewPagerAdapter(fm: FragmentManager?, id: Int) : FragmentPagerAdapter(fm) {

    var fragments: Array<Fragment> = arrayOf(
        MoviesFragment.newInstance(id),
        WatchlistFragment.newInstance(id)
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Movies"
            1 -> "Watchlist"
            else -> "Tab"
        }
    }
}