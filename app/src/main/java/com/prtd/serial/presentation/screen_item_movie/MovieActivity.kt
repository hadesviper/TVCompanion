package com.prtd.serial.presentation.screen_item_movie

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.prtd.serial.R
import com.prtd.serial.common.Constants.Back_Img_Url
import com.prtd.serial.common.Constants.Media_ID
import com.prtd.serial.common.Constants.Media_Name
import com.prtd.serial.common.HelperMethods
import com.prtd.serial.databinding.ActivityMovieBinding
import com.prtd.serial.presentation.screen_item_movie.adapters.SimilarMoviesAdapter
import com.prtd.serial.presentation.screen_item_movie.view_models.MovieViewModel
import com.prtd.serial.presentation.screen_item_movie.view_models.SimilarMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    private val similarMoviesViewModel: SimilarMoviesViewModel by viewModels()
    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie)
        binding.openYT = HelperMethods
        val movieName:String? = intent.getStringExtra(Media_Name)
        val movieID:Int = intent.getIntExtra(Media_ID,77)

        supportActionBar?.title = movieName

        movieViewModel.getMovie(movieID).run {
            movieViewModel.getIsLoading().observeForever{

            }
            movieViewModel.getResult().observeForever{
                HelperMethods.loadImageIntoView(this@MovieActivity, "$Back_Img_Url${it.backdrop_path}",binding.imgBG)
                binding.movie = it
            }
        }

        similarMoviesViewModel.getSimilarMovies(movieID).run {
            similarMoviesViewModel.getIsLoading().observeForever{
                if (!it){
                    binding.barSimilar.visibility = View.GONE
                }
            }
            similarMoviesViewModel.getResult().observeForever{
                binding.recyclerSimilar.layoutManager = LinearLayoutManager(this@MovieActivity, HORIZONTAL,false)
                binding.recyclerSimilar.adapter = SimilarMoviesAdapter(it)
            }
        }
    }
}