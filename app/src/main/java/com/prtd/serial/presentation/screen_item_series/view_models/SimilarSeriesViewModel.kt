package com.prtd.serial.presentation.screen_item_series.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.common.HelperMethods
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.SeriesResult
import com.prtd.serial.domain.use_cases.use_cases_remote.SimilarSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SimilarSeriesViewModel @Inject constructor(
    private val similarSeriesUseCase: SimilarSeriesUseCase
) :ViewModel() {
    private val loading      = MutableLiveData<Boolean>()
    private val result       = MutableLiveData<SeriesResult>()
    private val error = MutableLiveData<String>()
    private var itemID = 0

    fun getIsLoading(): LiveData<Boolean> {
        return loading
    }

    fun getResult(): LiveData<SeriesResult> {
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

    fun getSimilarSeries(id: Int, page: Int) {
        itemID = id
        similarSeriesUseCase(id, page).onEach {
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
        HelperMethods.recyclerViewsHandler(rv, condition) {
            getSimilarSeries(itemID, getCurrentPage() + 1)
        }
    }
}