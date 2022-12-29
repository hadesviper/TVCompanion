package com.prtd.serial.presentation.screen_search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.prtd.serial.R
import com.prtd.serial.common.Constants
import com.prtd.serial.databinding.ActivitySearchBinding
import com.prtd.serial.presentation.screen_search.adapters.ViewPagerAdapter
import com.prtd.serial.presentation.screen_search.movie_results_fragment.MovieResultViewModel
import com.prtd.serial.presentation.screen_search.series_results_fragment.SeriesResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val binding: ActivitySearchBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_search
        )
    }
    private val movieResultViewModel: MovieResultViewModel by viewModels()
    private val seriesResultViewModel: SeriesResultViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewPager()

        getResults(intent?.getStringExtra(Constants.Media_Name).toString())

        setUpSearchView()
    }

    private fun setUpSearchView() {
        binding.edtSearch.apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    getResults(query!!)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    private fun getResults(input: String) {
        movieResultViewModel.getAdapter().clearAll()
        seriesResultViewModel.getAdapter().clearAll()
        movieResultViewModel.getMovies(input, 1)
        seriesResultViewModel.getSeries(input, 1)
    }

    private fun setUpViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(this)
        binding.viewPager.currentItem = 1
        binding.viewPager.currentItem = 0
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "Movies"
                1 -> tab.text = "Series"
            }
        }.attach()
    }
}