package com.prtd.serial.presentation.screen_main.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.common.HelperMethods.recyclerViewsHandler
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.SeriesPopular
import com.prtd.serial.domain.use_cases.use_cases_remote.PopularSeriesUseCase
import com.prtd.serial.presentation.screen_main.adapters.PopularSeriesAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularSeriesViewModel @Inject constructor(
    private val popularSeriesUseCase: PopularSeriesUseCase
) :ViewModel() {
    private val loading = MutableLiveData<Boolean>()
    private val result = MutableLiveData<SeriesPopular>()
    private val error = MutableLiveData<String>()
    private val adapter = PopularSeriesAdapter()

    fun getAdapter() = adapter

    fun getIsLoading(): LiveData<Boolean> {
        return loading
    }

    fun getResult(): LiveData<SeriesPopular> {
        return result
    }

    fun getError(): LiveData<String> {
        return error
    }

    fun getCurrentPage(): Int {
        return if (result.value == null) {
            1
        } else {
            result.value!!.page
        }
    }

    fun getPopularSeries(page: Int) {
        popularSeriesUseCase(page).onEach {
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

    fun registerRecyclerView(rv: RecyclerView) {
        val condition =
            { !rv.canScrollHorizontally(RecyclerView.FOCUS_LEFT) && getIsLoading().value == false }
        recyclerViewsHandler(rv, condition) {
            getPopularSeries(getCurrentPage() + 1)
        }
    }
}