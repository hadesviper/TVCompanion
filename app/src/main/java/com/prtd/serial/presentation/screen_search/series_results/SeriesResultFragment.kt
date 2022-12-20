package com.prtd.serial.presentation.screen_search.series_results

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.prtd.serial.R
import com.prtd.serial.common.Constants
import com.prtd.serial.databinding.FragmentSeriesResultBinding
import com.prtd.serial.presentation.screen_search.MultiSearchViewModel
import com.prtd.serial.presentation.screen_search.series_results.adapters.ResultedSeriesAdapter


class SeriesResultFragment : Fragment(R.layout.fragment_series_result) {

    private lateinit var binding: FragmentSeriesResultBinding
    private lateinit var multiSearchViewModel: MultiSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesResultBinding.inflate(layoutInflater)
        multiSearchViewModel= ViewModelProvider(requireActivity())[MultiSearchViewModel::class.java]
        requireActivity().intent.getStringExtra(Constants.Media_Name).toString()

        binding.recyclerSeriesResult.layoutManager = GridLayoutManager(requireContext(),2)


        multiSearchViewModel.getResult().observe(requireActivity()){
            Log.i("resulted", "onCreateView:$it ")
            binding.recyclerSeriesResult.adapter = ResultedSeriesAdapter(it.results.filter {item->
                item.type == Constants.Type_Series
            })
        }
        multiSearchViewModel.getIsLoading().observe(requireActivity()){

        }
        return binding.root
    }

}