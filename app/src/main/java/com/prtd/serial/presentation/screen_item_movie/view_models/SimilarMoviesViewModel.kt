package com.prtd.serial.presentation.screen_item_movie.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.MoviesResult
import com.prtd.serial.domain.use_cases.SimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(
    private val similarMoviesUseCase: SimilarMoviesUseCase
) :ViewModel() {
    private val loading      = MutableLiveData<Boolean>()
    private val result       = MutableLiveData<MoviesResult>()
    private val error        = MutableLiveData<String>()

    fun getIsLoading(): LiveData<Boolean> {
        return loading
    }
    fun getResult(): LiveData<MoviesResult> {
        return result
    }
    fun getError(): LiveData<String> {
        return error
    }

    fun getSimilarMovies(id :Int){
        similarMoviesUseCase(id).onEach {
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