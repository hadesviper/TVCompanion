package com.prtd.serial.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prtd.serial.data.local.entities.EntityMovie
import com.prtd.serial.data.local.entities.EntitySeries

@Database(
    entities = [EntityMovie::class, EntitySeries::class],
    version = 1,
    exportSchema = false
)
abstract class WatchLaterDatabase : RoomDatabase() {
    abstract fun watchLaterDao(): WatchLaterDao

    companion object {
        const val DB_Name = "WatchLaterDB"
    }
}