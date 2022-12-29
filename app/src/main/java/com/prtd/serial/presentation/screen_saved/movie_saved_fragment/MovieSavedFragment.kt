package com.prtd.serial.presentation.screen_saved.movie_saved_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.prtd.serial.databinding.FragmentMovieSavedBinding
import com.prtd.serial.presentation.screen_saved.movie_saved_fragment.adapters.SavedMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSavedFragment : Fragment() {

    private val binding: FragmentMovieSavedBinding by lazy {
        FragmentMovieSavedBinding.inflate(
            layoutInflater
        )
    }
    private val moviesSavedViewModel: MoviesSavedViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.recyclerMoviesSaved.layoutManager = GridLayoutManager(requireContext(), 2)


        moviesSavedViewModel.apply {

            getSavedMovies().observe(requireActivity()) {
                binding.recyclerMoviesSaved.adapter = SavedMoviesAdapter(it)
            }

        }

        return binding.root
    }
}