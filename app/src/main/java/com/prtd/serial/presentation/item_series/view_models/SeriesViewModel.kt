package com.prtd.serial.presentation.item_series.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.Series
import com.prtd.serial.domain.use_cases.ShowSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val showSeriesUseCase: ShowSeriesUseCase
) :ViewModel() {
    private val loading      = MutableLiveData<Boolean>()
    private val result       = MutableLiveData<Series>()
    private val error        = MutableLiveData<String>()

    fun getIsLoading():LiveData <Boolean>{
        return loading
    }
    fun getResult():LiveData <Series>{
        return result
    }
    fun getError():LiveData <String>{
        return error
    }
    fun getSeries(id :Int){
        showSeriesUseCase.invoke(id).onEach {
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