package com.prtd.serial.presentation.screen_saved.series_saved_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.prtd.serial.databinding.FragmentSeriesSavedBinding
import com.prtd.serial.presentation.screen_saved.series_saved_fragment.adapters.SavedSeriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesSavedFragment : Fragment() {

    private val binding: FragmentSeriesSavedBinding by lazy {
        FragmentSeriesSavedBinding.inflate(
            layoutInflater
        )
    }

    private val seriesSavedViewModel by viewModels<SeriesSavedViewModel>({ requireActivity() })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.recyclerSeriesSaved.layoutManager = GridLayoutManager(requireContext(), 2)

        seriesSavedViewModel.apply {

            getSavedSeries().observe(requireActivity()) {
                binding.recyclerSeriesSaved.adapter = SavedSeriesAdapter(it)
            }

        }

        return binding.root
    }

}