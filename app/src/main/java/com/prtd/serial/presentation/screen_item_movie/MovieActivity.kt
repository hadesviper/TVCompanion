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
import com.prtd.serial.common.HelperMethods.loadImageIntoView
import com.prtd.serial.common.HelperMethods.showErrorDialog
import com.prtd.serial.databinding.ActivityMovieBinding
import com.prtd.serial.presentation.screen_item_movie.adapters.SimilarMoviesAdapter
import com.prtd.serial.presentation.screen_item_movie.view_models.MovieViewModel
import com.prtd.serial.presentation.screen_item_movie.view_models.SimilarMoviesViewModel
import com.prtd.serial.presentation.screen_saved.movie_saved_fragment.MoviesSavedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    private val similarMoviesViewModel: SimilarMoviesViewModel by viewModels()

    private val moviesSavedViewModel: MoviesSavedViewModel by viewModels()

    private val binding: ActivityMovieBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_movie
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieID: Int = intent.getIntExtra(Media_ID, 77)

        supportActionBar?.title = intent.getStringExtra(Media_Name)
        binding.run {
            watchLater = moviesSavedViewModel
            moviesSavedViewModel.saveButtonSet(btnSave, movieID, this@MovieActivity)
            openYT = HelperMethods
            recyclerSimilar.layoutManager =
                LinearLayoutManager(this@MovieActivity, HORIZONTAL, false)
        }
        movieViewModel.run {
            getMovie(movieID)
            getIsLoading().observe(this@MovieActivity) {

            }
            getResult().observe(this@MovieActivity) {
                loadImageIntoView(
                    this@MovieActivity,
                    "$Back_Img_Url${it.backdrop_path}",
                    binding.imgBG
                )
                binding.movie = it
            }
            getError().observe(this@MovieActivity) {
                showErrorDialog(this@MovieActivity, it) {
                    getMovie(movieID)
                }
            }

            similarMoviesViewModel.run {

                val adapter = SimilarMoviesAdapter()

                binding.recyclerSimilar.adapter = adapter

                getSimilarMovies(movieID, getCurrentPage())

                registerRecyclerView(binding.recyclerSimilar)

                getIsLoading().observe(this@MovieActivity) {
                    binding.barSimilar.visibility = if (it) View.VISIBLE else View.INVISIBLE
                }
                getResult().observe(this@MovieActivity) {
                    adapter.addItems(it.results as ArrayList)
                }
                getError().observe(this@MovieActivity) {
                    showErrorDialog(this@MovieActivity, it) {
                        getSimilarMovies(movieID, getCurrentPage())
                    }
                }
            }
        }
    }
}