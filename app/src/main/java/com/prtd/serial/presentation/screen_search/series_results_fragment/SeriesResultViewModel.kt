package com.prtd.serial.presentation.screen_search.series_results_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.common.HelperMethods.recyclerViewsHandler
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.SeriesResult
import com.prtd.serial.domain.use_cases.use_cases_remote.SearchSeriesUseCase
import com.prtd.serial.presentation.screen_search.series_results_fragment.adapters.ResultedSeriesAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeriesResultViewModel @Inject constructor(
    private val searchSeriesUseCase: SearchSeriesUseCase
) :ViewModel() {
    private val loading = MutableLiveData<Boolean>()
    private val result = MutableLiveData<SeriesResult>()
    private val error = MutableLiveData<String>()
    private var seriesName = ""
    private val adapter = ResultedSeriesAdapter()

    fun getAdapter() = adapter

    fun getIsLoading(): LiveData<Boolean> {
        return loading
    }

    fun getResult(): LiveData<SeriesResult> {
        return result
    }

    fun getError(): LiveData<String> {
        return error
    }
    fun getSeries(name :String,page:Int) {
        seriesName = name
        searchSeriesUseCase(name, page).onEach {
            when (it) {
                is Resources.Loading -> {
                    loading.value = true
                }
                is Resources.Success -> {
                    loading.value = false
                    result.value = it.data!!
                }
                is Resources.Error -> {
                    loading.value = false
                    error.value = it.message!!
                }

            }

        }.launchIn(viewModelScope)
    }

    fun getCurrentPage(): Int {
        return if (result.value == null) {
            1
        } else {
            result.value!!.page + 1
        }
    }

    fun registerRecyclerView(rv: RecyclerView) {
        val condition =
            { !rv.canScrollVertically(RecyclerView.FOCUS_DOWN) && getIsLoading().value == false }
        recyclerViewsHandler(rv, condition) {
            getSeries(seriesName, getCurrentPage() + 1)
        }
    }
}