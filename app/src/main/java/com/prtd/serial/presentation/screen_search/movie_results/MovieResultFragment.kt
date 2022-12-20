package com.prtd.serial.presentation.screen_search.movie_results

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.prtd.serial.R
import com.prtd.serial.common.Constants
import com.prtd.serial.common.HelperMethods.showErrorDialog
import com.prtd.serial.databinding.FragmentMovieResultBinding
import com.prtd.serial.presentation.screen_search.MultiSearchViewModel
import com.prtd.serial.presentation.screen_search.movie_results.adapters.ResultedMoviesAdapter


class MovieResultFragment : Fragment(R.layout.fragment_movie_result) {

    private lateinit var binding: FragmentMovieResultBinding
    private lateinit var multiSearchViewModel: MultiSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieResultBinding.inflate(layoutInflater)
        multiSearchViewModel =
            ViewModelProvider(requireActivity())[MultiSearchViewModel::class.java]
        requireActivity().intent.getStringExtra(Constants.Media_Name).toString()

        binding.recyclerMoviesResult.layoutManager = GridLayoutManager(requireContext(), 2)


        multiSearchViewModel.apply {

            getResult().observe(requireActivity()) {
                Log.i("resulted", "onCreateView:$it ")
                binding.recyclerMoviesResult.adapter =
                    ResultedMoviesAdapter(it.results.filter { item ->
                        item.type == Constants.Type_Movie
                    })
            }

            getIsLoading().observe(requireActivity()) {
                binding.barLoadingMovies.isVisible = it
            }
            getError().observe(requireActivity()) {
                showErrorDialog(requireContext(), it) {
                }
            }
        }
        return binding.root
    }
}