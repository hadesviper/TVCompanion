package com.prtd.serial.presentation.search_screen.series_results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.SeriesResult
import com.prtd.serial.domain.use_cases.SearchSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeriesResultViewModel @Inject constructor(
    private val searchSeriesUseCase: SearchSeriesUseCase
) :ViewModel() {
    private val loading      = MutableLiveData<Boolean>()
    private val result       = MutableLiveData<SeriesResult>()
    private val error        = MutableLiveData<String>()

    fun getIsLoading():LiveData <Boolean>{
        return loading
    }
    fun getResult():LiveData <SeriesResult>{
        return result
    }
    fun getError():LiveData <String>{
        return error
    }
    fun getSeries(name :String,page:Int){
        searchSeriesUseCase.invoke(name,page).onEach {
            when (it) {
                is Resources.Loading -> {
                    loading.value = true
                }
                is Resources.Success -> {
                    loading.value = false
                    result.value = it.data
                }
                is Resources.Error -> {
                    loading.value = false
                    error.value = it.message
                }

            }

        }.launchIn(viewModelScope)
    }

}