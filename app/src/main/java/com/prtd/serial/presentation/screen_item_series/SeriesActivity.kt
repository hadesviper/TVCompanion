package com.prtd.serial.presentation.screen_item_series

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.R
import com.prtd.serial.common.Constants
import com.prtd.serial.common.HelperMethods
import com.prtd.serial.common.HelperMethods.loadImageIntoView
import com.prtd.serial.common.HelperMethods.showErrorDialog
import com.prtd.serial.databinding.ActivitySeriesBinding
import com.prtd.serial.presentation.screen_item_series.adapters.SeasonsAdapter
import com.prtd.serial.presentation.screen_item_series.adapters.SimilarSeriesAdapter
import com.prtd.serial.presentation.screen_item_series.view_models.SeriesViewModel
import com.prtd.serial.presentation.screen_item_series.view_models.SimilarSeriesViewModel
import com.prtd.serial.presentation.screen_saved.series_saved_fragment.SeriesSavedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesActivity : AppCompatActivity() {
    private val binding: ActivitySeriesBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_series
        )
    }
    private val seriesViewModel: SeriesViewModel by viewModels()
    private val seriesSavedViewModel: SeriesSavedViewModel by viewModels()
    private val similarSeriesViewModel: SimilarSeriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val seriesID: Int = intent.getIntExtra(Constants.Media_ID, 119051)

        supportActionBar?.title = intent.getStringExtra(Constants.Media_Name)
        binding.run {
            openYT = HelperMethods
            watchLater = seriesSavedViewModel
            seriesSavedViewModel.saveButtonSet(btnSave, seriesID, this@SeriesActivity)
            recyclerSimilar.layoutManager =
                LinearLayoutManager(this@SeriesActivity, RecyclerView.HORIZONTAL, false)
            recyclerSeasons.layoutManager =
                LinearLayoutManager(this@SeriesActivity, RecyclerView.HORIZONTAL, false)
        }


        seriesViewModel.run {
            getSeries(seriesID)
            getResult().observe(this@SeriesActivity) {
                loadImageIntoView(
                    this@SeriesActivity,
                    "${Constants.Back_Img_Url}${it.backdrop_path}",
                    binding.imgBG
                )
                binding.series = it
                binding.recyclerSeasons.adapter = SeasonsAdapter(it)
            }
            getError().observe(this@SeriesActivity) {
                showErrorDialog(this@SeriesActivity, it) {
                    getSeries(seriesID)
                }
            }
        }

        similarSeriesViewModel.run {
            val adapter = SimilarSeriesAdapter()

            binding.recyclerSimilar.adapter = adapter

            getSimilarSeries(seriesID, getCurrentPage())

            registerRecyclerView(binding.recyclerSimilar)

            getIsLoading().observe(this@SeriesActivity) {
                binding.barSimilar.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
            getResult().observe(this@SeriesActivity) {
                adapter.addItems(it.results as ArrayList)
            }
            getError().observe(this@SeriesActivity) {
                showErrorDialog(this@SeriesActivity, it) {
                    getSimilarSeries(seriesID, getCurrentPage())
                }
            }
        }
    }
}