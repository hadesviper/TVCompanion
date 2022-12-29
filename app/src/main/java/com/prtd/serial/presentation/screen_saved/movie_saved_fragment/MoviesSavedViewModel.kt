package com.prtd.serial.presentation.screen_saved.movie_saved_fragment

import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat.getDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.prtd.serial.R
import com.prtd.serial.data.local.entities.EntityMovie
import com.prtd.serial.domain.models.Movie
import com.prtd.serial.domain.use_cases.use_cases_local.SavedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesSavedViewModel @Inject constructor(
    private val savedMoviesUseCase: SavedMoviesUseCase
) : ViewModel() {


    private fun getItemExists(id: Int): LiveData<Boolean> = liveData {
        emit(savedMoviesUseCase.checkMovieExistence(id))
    }

    fun getSavedMovies(): LiveData<List<EntityMovie>> {
        return savedMoviesUseCase.getAllMovies()
    }

    private fun removeMovie(id: Int) {
        viewModelScope.launch {
            savedMoviesUseCase.removeMovie(id)
        }
    }

    private fun addMovie(movie: EntityMovie) {
        viewModelScope.launch {
            savedMoviesUseCase.addMovie(movie)
        }
    }

    fun saveButtonClick(btn: ImageButton, movie: Movie? = null, context: Context) {
        if (movie != null) {
            getItemExists(movie.id).observeForever {
                if (it) {
                    removeMovie(movie.id)
                    Toast.makeText(context, "Removed from watch later", Toast.LENGTH_SHORT).show()
                    btn.setImageDrawable(
                        getDrawable(
                            context,
                            R.drawable.ic_baseline_bookmark_border_24
                        )
                    )
                } else {
                    addMovie(movie.toEntityMovie())
                    Toast.makeText(context, "Added to watch later", Toast.LENGTH_SHORT).show()
                    btn.setImageDrawable(
                        getDrawable(
                            context,
                            R.drawable.ic_baseline_bookmark_added_24
                        )
                    )
                }
            }
        }

    }

    fun saveButtonSet(btn: ImageButton, movieID: Int, context: Context) {
        getItemExists(movieID).observeForever {
            if (it) {
                btn.setImageDrawable(getDrawable(context, R.drawable.ic_baseline_bookmark_added_24))
            } else {
                btn.setImageDrawable(
                    getDrawable(
                        context,
                        R.drawable.ic_baseline_bookmark_border_24
                    )
                )
            }
        }
    }

}