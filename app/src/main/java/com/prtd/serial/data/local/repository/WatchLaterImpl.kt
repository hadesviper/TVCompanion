package com.prtd.serial.data.local.repository

import androidx.lifecycle.LiveData
import com.prtd.serial.data.local.WatchLaterDao
import com.prtd.serial.data.local.entities.EntityMovie
import com.prtd.serial.data.local.entities.EntitySeries
import com.prtd.serial.domain.repository.WatchLaterRepo
import javax.inject.Inject

class WatchLaterImpl @Inject constructor(
    private val watchLaterDao: WatchLaterDao
) : WatchLaterRepo {
    override suspend fun addMovie(movie: EntityMovie) {
        watchLaterDao.addMovie(movie)
    }

    override suspend fun removeMovie(id: Int) {
        watchLaterDao.removeMovie(id)
    }

    override suspend fun checkMovieExistence(id: Int): Byte {
        return watchLaterDao.checkMovieExistence(id)
    }

    override fun getAllMovies(): LiveData<List<EntityMovie>> {
        return watchLaterDao.getAllMovies()
    }

    override suspend fun addSeries(series: EntitySeries) {
        watchLaterDao.addSeries(series)
    }

    override suspend fun removeSeries(id: Int) {
        watchLaterDao.removeSeries(id)
    }

    override suspend fun checkSeriesExistence(id: Int): Byte {
        return watchLaterDao.checkSeriesExistence(id)
    }

    override fun getAllSeries(): LiveData<List<EntitySeries>> {
        return watchLaterDao.getAllSeries()
    }

}