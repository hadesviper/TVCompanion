package com.prtd.serial.presentation.screen_saved

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.prtd.serial.R
import com.prtd.serial.databinding.ActivitySavedBinding
import com.prtd.serial.presentation.screen_saved.adapters.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedActivity : AppCompatActivity() {
    private val binding: ActivitySavedBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_saved
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewPager()

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