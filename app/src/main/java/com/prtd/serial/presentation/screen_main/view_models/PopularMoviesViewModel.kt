package com.prtd.serial.presentation.screen_main.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.MoviesPopular
import com.prtd.serial.domain.use_cases.use_cases_remote.PopularMoviesUseCase
import com.prtd.serial.presentation.screen_main.adapters.PopularMoviesAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val popularMoviesUseCase: PopularMoviesUseCase
) :ViewModel() {
    private val loading = MutableLiveData<Boolean>()
    private val result = MutableLiveData<MoviesPopular>()
    private val error = MutableLiveData<String>()
    private val adapter = PopularMoviesAdapter()

    fun getAdapter() = adapter

    fun getIsLoading(): LiveData<Boolean> {
        return loading
    }

    fun getResult(): LiveData<MoviesPopular> {
        return result
    }

    fun getError(): LiveData<String> {
        return error
    }

    fun getPopularMovies(page: Int) {
        popularMoviesUseCase(page).onEach {
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
            result.value!!.page
        }
    }

    fun registerRecyclerView(rv: RecyclerView) {
        rv.run {
            addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!canScrollHorizontally(recyclerView.left) && getIsLoading().value != true) {
                            getPopularMovies(getCurrentPage() + 1)
                        }
                    }
                }
            )
        }
    }

}