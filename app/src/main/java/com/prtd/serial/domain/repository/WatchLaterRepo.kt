package com.prtd.serial.domain.repository

import androidx.lifecycle.LiveData
import com.prtd.serial.data.local.entities.EntityMovie
import com.prtd.serial.data.local.entities.EntitySeries

interface WatchLaterRepo {
    suspend fun addMovie(movie: EntityMovie)

    suspend fun removeMovie(id: Int)

    suspend fun checkMovieExistence(id: Int): Byte

    fun getAllMovies(): LiveData<List<EntityMovie>>

    suspend fun addSeries(series: EntitySeries)

    suspend fun removeSeries(id: Int)

    suspend fun checkSeriesExistence(id: Int): Byte

    fun getAllSeries(): LiveData<List<EntitySeries>>
}