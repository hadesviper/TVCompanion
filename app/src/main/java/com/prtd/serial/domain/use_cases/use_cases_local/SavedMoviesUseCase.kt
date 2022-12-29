package com.prtd.serial.domain.use_cases.use_cases_local

import androidx.lifecycle.LiveData
import com.prtd.serial.data.local.entities.EntityMovie
import com.prtd.serial.domain.repository.WatchLaterRepo
import javax.inject.Inject

class SavedMoviesUseCase @Inject constructor(
    private val watchLaterRepo: WatchLaterRepo
) {
    suspend fun addMovie(item: EntityMovie) {
        watchLaterRepo.addMovie(item)
    }

    suspend fun removeMovie(id: Int) {
        watchLaterRepo.removeMovie(id)
    }

    suspend fun checkMovieExistence(id: Int): Boolean {
        return (watchLaterRepo.checkMovieExistence(id) == 1.toByte())
    }

    fun getAllMovies(): LiveData<List<EntityMovie>> {
        return watchLaterRepo.getAllMovies()
    }

}