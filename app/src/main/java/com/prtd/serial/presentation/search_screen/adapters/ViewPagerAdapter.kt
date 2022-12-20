package com.prtd.serial.presentation.search_screen.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.prtd.serial.presentation.search_screen.movie_results.MovieResultFragment
import com.prtd.serial.presentation.search_screen.series_results.SeriesResultFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return MovieResultFragment()
            1 -> return SeriesResultFragment()
        }
        return MovieResultFragment()
    }

}