package com.moviesapp.mymoviesapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentList : MutableList<Fragment> = ArrayList()
    private val tittleList : MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        tittleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tittleList[position]
    }
}