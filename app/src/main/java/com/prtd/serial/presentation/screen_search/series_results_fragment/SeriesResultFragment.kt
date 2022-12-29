package com.prtd.serial.presentation.screen_search.series_results_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.prtd.serial.common.HelperMethods.showErrorDialog
import com.prtd.serial.databinding.FragmentSeriesResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesResultFragment : Fragment() {

    private val binding: FragmentSeriesResultBinding by lazy {
        FragmentSeriesResultBinding.inflate(
            layoutInflater
        )
    }

    private val seriesResultViewModel by viewModels<SeriesResultViewModel>({ requireActivity() })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.recyclerSeriesResult.layoutManager = GridLayoutManager(requireContext(), 2)

        seriesResultViewModel.run {
            binding.recyclerSeriesResult.adapter = getAdapter()

            registerRecyclerView(binding.recyclerSeriesResult)

            getResult().observe(requireActivity()) {
                getAdapter().addItems(it.results as ArrayList)
            }

            getIsLoading().observe(requireActivity()) {
                binding.barLoadingSeries.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }

            getError().observe(requireActivity()) {
                showErrorDialog(requireContext(), it) {

                }
            }
        }

        return binding.root
    }

}