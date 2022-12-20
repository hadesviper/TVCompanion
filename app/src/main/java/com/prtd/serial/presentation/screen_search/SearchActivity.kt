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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val multiSearchViewModel:MultiSearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_search)
        binding.viewPager.adapter = ViewPagerAdapter(this)
        binding.viewPager.currentItem = 1
        binding.viewPager.currentItem = 0
        TabLayoutMediator(binding.tabLayout,binding.viewPager
        ) { tab, position ->
            when(position){
                0 -> tab.text = "Movies"
                1 -> tab.text = "Series"
            }
        }.attach()
        multiSearchViewModel.searchAll(intent?.getStringExtra(Constants.Media_Name).toString(),1)
        binding.etdSearch.apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(object: SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    multiSearchViewModel.searchAll(query.toString(),1)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }
}