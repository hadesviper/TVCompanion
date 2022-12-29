package com.prtd.serial.presentation.screen_search.movie_results_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.prtd.serial.common.HelperMethods.showErrorDialog
import com.prtd.serial.databinding.FragmentMovieResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieResultFragment : Fragment() {

    private val binding: FragmentMovieResultBinding by lazy {
        FragmentMovieResultBinding.inflate(
            layoutInflater
        )
    }
    private val movieResultViewModel: MovieResultViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.recyclerMoviesResult.layoutManager = GridLayoutManager(requireContext(), 2)


        movieResultViewModel.apply {

            binding.recyclerMoviesResult.adapter = getAdapter()
            registerRecyclerView(binding.recyclerMoviesResult)

            getResult().observe(requireActivity()) {
                getAdapter().addItems(it.results as ArrayList)
            }

            getIsLoading().observe(requireActivity()) {
                binding.barLoadingMovies.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
            getError().observe(requireActivity()) {
                showErrorDialog(requireContext(), it) {
                }
            }
        }

        return binding.root
    }
}