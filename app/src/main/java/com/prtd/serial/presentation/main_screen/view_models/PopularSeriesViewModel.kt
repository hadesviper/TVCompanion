package com.prtd.serial.presentation.main_screen.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.SeriesPopular
import com.prtd.serial.domain.use_cases.PopularSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularSeriesViewModel @Inject constructor(
    private val popularSeriesUseCase: PopularSeriesUseCase
) :ViewModel() {
    private val loading    = MutableLiveData<Boolean>()
    private val result       = MutableLiveData<SeriesPopular>()
    private val error        = MutableLiveData<String>()

    fun getIsLoading(): LiveData<Boolean> {
        return loading
    }
    fun getResult(): LiveData<SeriesPopular> {
        return result
    }
    fun getError(): LiveData<String> {
        return error
    }
    fun getPopularSeries(page:Int){
        popularSeriesUseCase.invoke(page).onEach {
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