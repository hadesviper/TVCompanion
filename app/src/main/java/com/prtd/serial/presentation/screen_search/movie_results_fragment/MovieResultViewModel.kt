package com.prtd.serial.presentation.screen_search.movie_results_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.common.HelperMethods
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.MoviesResult
import com.prtd.serial.domain.use_cases.use_cases_remote.SearchMoviesUseCase
import com.prtd.serial.presentation.screen_search.movie_results_fragment.adapters.ResultedMoviesAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieResultViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) :ViewModel() {
    private val loading = MutableLiveData<Boolean>()
    private val result = MutableLiveData<MoviesResult>()
    private val error = MutableLiveData<String>()
    private var movieName = ""
    private val adapter = ResultedMoviesAdapter()

    fun getAdapter() = adapter

    fun getIsLoading(): LiveData<Boolean> {
        return loading
    }

    fun getResult(): LiveData<MoviesResult> {
        return result
    }

    fun getError(): LiveData<String> {
        return error
    }
    fun getMovies(name :String,page:Int) {
        movieName = name
        searchMoviesUseCase(name, page).onEach {
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
        HelperMethods.recyclerViewsHandler(rv, condition) {
            getMovies(movieName, getCurrentPage() + 1)
        }
    }


}