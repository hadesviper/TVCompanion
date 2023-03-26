package com.prtd.serial.presentation.screen_main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.prtd.serial.R
import com.prtd.serial.common.HelperMethods.showAboutDialog
import com.prtd.serial.common.HelperMethods.showErrorDialog
import com.prtd.serial.common.HelperMethods.showExitDialog
import com.prtd.serial.databinding.ActivityMainBinding
import com.prtd.serial.presentation.screen_main.view_models.*
import com.prtd.serial.presentation.screen_saved.SavedActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()
    private val popularSeriesViewModel: PopularSeriesViewModel by viewModels()
    private val topRatedMoviesViewModel: TopRatedMoviesViewModel by viewModels()
    private val topRatedSeriesViewModel: TopRatedSeriesViewModel by viewModels()
    private val multiSearchViewModel: MultiSearchViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpBindingVariables()

        setUpRecyclersLayoutManagers()

        setUpViewModels()

    }

    private fun setUpViewModels() {

        popularSeriesViewModel.apply {

            binding.recyclerPopularSeries.adapter = getAdapter()

            registerRecyclerView(binding.recyclerPopularSeries)

            getPopularSeries(getCurrentPage())

            getIsLoading().observe(this@MainActivity) {
                binding.barPopularSeries.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
            getResult().observe(this@MainActivity) {
                getAdapter().addItems(it.results as ArrayList)
            }
            getError().observe(this@MainActivity) {
                showErrorDialog(this@MainActivity, it) {
                    getPopularSeries(getCurrentPage())
                }
            }
        }

        popularMoviesViewModel.apply {

            binding.recyclerPopularMovies.adapter = getAdapter()

            registerRecyclerView(binding.recyclerPopularMovies)

            getPopularMovies(getCurrentPage())

            getIsLoading().observe(this@MainActivity) {
                binding.barPopularMovies.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
            getResult().observe(this@MainActivity) {
                getAdapter().addItems(it.results as ArrayList)
            }
            getError().observe(this@MainActivity) {
                showErrorDialog(this@MainActivity, it) {
                    getPopularMovies(getCurrentPage())
                }
            }
        }

        topRatedSeriesViewModel.apply {

            binding.recyclerTopRatedSeries.adapter = getAdapter()

            registerRecyclerView(binding.recyclerTopRatedSeries)

            getTopRatedSeries(getCurrentPage())

            getIsLoading().observe(this@MainActivity) {
                binding.barTopRatedSeries.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
            getResult().observe(this@MainActivity) {
                getAdapter().addItems(it.results as ArrayList)
            }
            getError().observe(this@MainActivity) {
                showErrorDialog(this@MainActivity, it) {
                    getTopRatedSeries(getCurrentPage())
                }
            }
        }

        topRatedMoviesViewModel.apply {

            binding.recyclerTopRatedMovies.adapter = getAdapter()

            registerRecyclerView(binding.recyclerTopRatedMovies)

            getTopRatedMovies(getCurrentPage())

            getIsLoading().observe(this@MainActivity) {
                binding.barTopRatedMovies.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
            getResult().observe(this@MainActivity) {
                getAdapter().addItems(it.results as ArrayList)
            }
            getError().observe(this@MainActivity) {
                showErrorDialog(this@MainActivity, it) {
                    getTopRatedMovies(getCurrentPage())
                }
            }
        }

    }

    private fun setUpRecyclersLayoutManagers() {
        binding.recyclerPopularMovies.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.recyclerPopularSeries.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.recyclerTopRatedMovies.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.recyclerTopRatedSeries.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
    }

    private fun setUpBindingVariables() {
        val popupMenu = PopupMenu(this, binding.btnMenu)
        binding.btnMenu.setOnClickListener {
            popupMenu.show()
        }
        popupMenu.inflate(R.menu.menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.about -> showAboutDialog(this)
                R.id.later -> startActivity(Intent(this, SavedActivity::class.java))
                R.id.more -> {
                    startActivity(
                        Intent(Intent.ACTION_VIEW)
                            .setData(
                                Uri.parse("https://play.google.com/store/apps/developer?id=Herald")
                            )
                    )
                }
                R.id.exit -> showExitDialog(this)
            }
            true
        }
        multiSearchViewModel.registerAutoComplete(binding.autoComplete)
        binding.multiSearchViewModel = multiSearchViewModel
        binding.arrayAdapter = ArrayAdapter<String>(
            this@MainActivity,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item
        )
    }

}
