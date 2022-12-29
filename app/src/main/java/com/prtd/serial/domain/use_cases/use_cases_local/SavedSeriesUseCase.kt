package com.prtd.serial.domain.use_cases.use_cases_local

import androidx.lifecycle.LiveData
import com.prtd.serial.data.local.entities.EntitySeries
import com.prtd.serial.domain.repository.WatchLaterRepo
import javax.inject.Inject

class SavedSeriesUseCase @Inject constructor(
    private val watchLaterRepo: WatchLaterRepo
) {
    suspend fun addSeries(item: EntitySeries) {
        watchLaterRepo.addSeries(item)
    }

    suspend fun removeSeries(id: Int) {
        watchLaterRepo.removeSeries(id)
    }

    suspend fun checkSeriesExistence(id: Int): Boolean {
        return (watchLaterRepo.checkSeriesExistence(id) == 1.toByte())
    }

    fun getAllSeries(): LiveData<List<EntitySeries>> {
        return watchLaterRepo.getAllSeries()
    }
}