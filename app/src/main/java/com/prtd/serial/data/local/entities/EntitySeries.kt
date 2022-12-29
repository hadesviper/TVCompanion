package com.prtd.serial.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntitySeries(
    @PrimaryKey
    val id: Int,
    val posterPath: String?,
    val name: String,
    val year: String?,
    val vote: Float,
    val timeStamp: Long = System.currentTimeMillis()
)


