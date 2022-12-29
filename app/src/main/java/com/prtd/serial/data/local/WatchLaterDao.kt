package com.prtd.serial.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prtd.serial.data.local.entities.EntityMovie
import com.prtd.serial.data.local.entities.EntitySeries

@Dao
interface WatchLaterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: EntityMovie)

    @Query("Delete from EntityMovie where id = :id")
    suspend fun removeMovie(id: Int)

    @Query("Select Exists (Select id from EntityMovie where id = :id)")
    suspend fun checkMovieExistence(id: Int): Byte

    @Query("Select * from EntityMovie order by timeStamp desc")
    fun getAllMovies(): LiveData<List<EntityMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSeries(series: EntitySeries)

    @Query("Delete from EntitySeries where id = :id")
    suspend fun removeSeries(id: Int)

    @Query("Select Exists (Select id from EntitySeries where id = :id)")
    suspend fun checkSeriesExistence(id: Int): Byte

    @Query("Select * from EntitySeries order by timeStamp desc")
    fun getAllSeries(): LiveData<List<EntitySeries>>
}