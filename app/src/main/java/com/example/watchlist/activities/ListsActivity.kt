package com.example.watchlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.watchlist.R
import com.example.watchlist.adapters.ViewPagerAdapter
import com.example.watchlist.utils.EXTRA_USER_ID
import com.google.android.material.tabs.TabLayout

class ListsActivity : AppCompatActivity() {

    private val TAG = ListsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)

        val userId = intent.getIntExtra(EXTRA_USER_ID, -1)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, userId)

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}
