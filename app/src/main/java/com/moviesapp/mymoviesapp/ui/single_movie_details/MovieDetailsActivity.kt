package com.moviesapp.mymoviesapp.ui.single_movie_details

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.moviesapp.mymoviesapp.R
import com.moviesapp.mymoviesapp.ui.adapter.PagerAdapter
import com.moviesapp.mymoviesapp.ui.fragment.MovieComment
import com.moviesapp.mymoviesapp.ui.fragment.MovieDetail

class MovieDetailsActivity: AppCompatActivity() {

    private lateinit var viewPager : ViewPager
    private lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        viewPager = findViewById(R.id.pager_Movie)
        tabLayout = findViewById(R.id.tab_Movie)

        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(MovieDetail(),"Information")
        adapter.addFragment(MovieComment(),"Comment")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.get(0).isEnabled = true
    }
}