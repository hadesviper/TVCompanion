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
import com.prtd.serial.databinding.ActivitySeriesBinding
import com.prtd.serial.presentation.screen_item_series.adapters.SeasonsAdapter
import com.prtd.serial.presentation.screen_item_series.adapters.SimilarSeriesAdapter
import com.prtd.serial.presentation.screen_item_series.view_models.SeriesViewModel
import com.prtd.serial.presentation.screen_item_series.view_models.SimilarSeriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeriesBinding
    private val seriesViewModel: SeriesViewModel by viewModels()
    private val similarSeriesViewModel: SimilarSeriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_series)
        binding.openYT = HelperMethods
        val seriesName:String? = intent.getStringExtra(Constants.Media_Name)
        val seriesID:Int = intent.getIntExtra(Constants.Media_ID,119051)

        supportActionBar?.title = seriesName

        seriesViewModel.getSeries(seriesID).run {
            seriesViewModel.getResult().observeForever{
                loadImageIntoView(this@SeriesActivity, "${Constants.Back_Img_Url}${it.backdrop_path}",binding.imgBG)
                binding.series = it
                binding.recyclerSeasons.layoutManager = LinearLayoutManager(this@SeriesActivity, RecyclerView.HORIZONTAL,false)
                binding.recyclerSeasons.adapter = SeasonsAdapter(it)
            }
        }

        similarSeriesViewModel.getSimilarSeries(seriesID).run {
            similarSeriesViewModel.getIsLoading().observeForever{
                if (!it){
                    binding.barSimilar.visibility = View.GONE
                }
            }
            similarSeriesViewModel.getResult().observeForever{
                binding.recyclerSimilar.layoutManager = LinearLayoutManager(this@SeriesActivity, RecyclerView.HORIZONTAL,false)
                binding.recyclerSimilar.adapter = SimilarSeriesAdapter(it)
            }
        }
    }
}