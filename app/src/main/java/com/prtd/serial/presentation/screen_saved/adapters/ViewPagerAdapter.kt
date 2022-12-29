package com.prtd.serial.presentation.screen_saved.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.prtd.serial.presentation.screen_saved.movie_saved_fragment.MovieSavedFragment
import com.prtd.serial.presentation.screen_saved.series_saved_fragment.SeriesSavedFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MovieSavedFragment()
            1 -> return SeriesSavedFragment()
        }
        return MovieSavedFragment()
    }

}