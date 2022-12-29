package com.prtd.serial.presentation.screen_saved.series_saved_fragment

import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat.getDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.prtd.serial.R
import com.prtd.serial.data.local.entities.EntitySeries
import com.prtd.serial.domain.models.Series
import com.prtd.serial.domain.use_cases.use_cases_local.SavedSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesSavedViewModel @Inject constructor(
    private val savedSeriesUseCase: SavedSeriesUseCase
) : ViewModel() {


    private fun getItemExists(id: Int): LiveData<Boolean> = liveData {
        emit(savedSeriesUseCase.checkSeriesExistence(id))
    }

    fun getSavedSeries(): LiveData<List<EntitySeries>> {
        return savedSeriesUseCase.getAllSeries()
    }

    private fun removeSeries(id: Int) {
        viewModelScope.launch {
            savedSeriesUseCase.removeSeries(id)
        }
    }

    private fun addSeries(series: EntitySeries) {
        viewModelScope.launch {
            savedSeriesUseCase.addSeries(series)
        }
    }

    fun saveButtonClick(btn: ImageButton, series: Series? = null, context: Context) {
        if (series != null) {
            getItemExists(series.id).observeForever {
                if (it) {
                    removeSeries(series.id)
                    Toast.makeText(context, "Removed from watch later", Toast.LENGTH_SHORT).show()
                    btn.setImageDrawable(
                        getDrawable(
                            context,
                            R.drawable.ic_baseline_bookmark_border_24
                        )
                    )
                } else {
                    addSeries(series.toEntitySeries())
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

    fun saveButtonSet(btn: ImageButton, seriesID: Int, context: Context) {
        getItemExists(seriesID).observeForever {
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