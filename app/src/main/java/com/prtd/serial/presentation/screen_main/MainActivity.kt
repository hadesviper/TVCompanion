package com.prtd.serial.presentation.screen_main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.prtd.serial.R
import com.prtd.serial.databinding.ActivityMainBinding
import com.prtd.serial.presentation.screen_main.adapters.PopularMoviesAdapter
import com.prtd.serial.presentation.screen_main.adapters.PopularSeriesAdapter
import com.prtd.serial.presentation.screen_main.adapters.TopRatedMoviesAdapter
import com.prtd.serial.presentation.screen_main.adapters.TopRatedSeriesAdapter
import com.prtd.serial.presentation.screen_main.view_models.PopularMoviesViewModel
import com.prtd.serial.presentation.screen_main.view_models.PopularSeriesViewModel
import com.prtd.serial.presentation.screen_main.view_models.TopRatedMoviesViewModel
import com.prtd.serial.presentation.screen_main.view_models.TopRatedSeriesViewModel
import com.prtd.serial.presentation.screen_search.MultiSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    private val popularMoviesViewModel : PopularMoviesViewModel by viewModels()
    private val popularSeriesViewModel : PopularSeriesViewModel by viewModels()
    private val topRatedMoviesViewModel: TopRatedMoviesViewModel by viewModels()
    private val topRatedSeriesViewModel: TopRatedSeriesViewModel by viewModels()
    private val multiSearchViewModel: MultiSearchViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        multiSearchViewModel.registerAutoComplete(binding.autoComplete)
        binding.multiSearchViewModel = multiSearchViewModel

        binding.arrayAdapter = ArrayAdapter<String> (this@MainActivity, com.google.android.material.R.layout.support_simple_spinner_dropdown_item)


        binding.recyclerPopularMovies.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        binding.recyclerPopularSeries.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        binding.recyclerTopRatedMovies.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        binding.recyclerTopRatedSeries.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)




        popularMoviesViewModel.getPopularMovies().apply {
            popularMoviesViewModel.getIsLoading().observeForever {
                if (!it){
                    binding.barPopularMovies.visibility = View.GONE
                }
            }
            popularMoviesViewModel.getResult().observeForever{
                binding.recyclerPopularMovies.adapter = PopularMoviesAdapter(it)
                Log.i("TAG", "onCreate Result: $it")
            }
            popularMoviesViewModel.getError().observeForever{
                Log.i("TAG", "onCreate Error: $it")
            }
        }

        popularSeriesViewModel.getPopularSeries(1).apply {
            popularSeriesViewModel.getIsLoading().observeForever {
                if (!it){
                    binding.barPopularSeries.visibility = View.GONE
                }
            }
            popularSeriesViewModel.getResult().observeForever{
                binding.recyclerPopularSeries.adapter = PopularSeriesAdapter(it)
                Log.i("TAG", "onCreate Result: $it")
            }
            popularSeriesViewModel.getError().observeForever{
                Log.i("TAG", "onCreate Error: $it")
            }
        }

        topRatedSeriesViewModel.getTopRatedSeries().apply {
            topRatedSeriesViewModel.getIsLoading().observeForever {
                if (!it){
                    binding.barTopRatedSeries.visibility = View.GONE
                }
            }
            topRatedSeriesViewModel.getResult().observeForever{
                binding.recyclerTopRatedSeries.adapter = TopRatedSeriesAdapter(it)
                Log.i("TAG", "onCreate Result: $it")
            }
            topRatedSeriesViewModel.getError().observeForever{
                Log.i("TAG", "onCreate Error: $it")
            }
        }

        topRatedMoviesViewModel.getTopRatedMovies().apply {
            topRatedMoviesViewModel.getIsLoading().observeForever {
                if (!it){
                    binding.barTopRatedMovies.visibility = View.GONE
                }
            }
            topRatedMoviesViewModel.getResult().observeForever{
                binding.recyclerTopRatedMovies.adapter = TopRatedMoviesAdapter(it)

                Log.i("TAG", "onCreate Result: $it")
            }
            topRatedMoviesViewModel.getError().observeForever{
                Log.i("TAG", "onCreate Error: $it")
            }
        }

    }

}
