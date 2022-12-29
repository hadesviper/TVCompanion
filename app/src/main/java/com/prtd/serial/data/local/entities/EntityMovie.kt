package com.prtd.serial.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityMovie(
    @PrimaryKey
    val id: Int,
    val posterPath: String?,
    val year: String?,
    val title: String,
    val vote: Float,
    val timeStamp: Long = System.currentTimeMillis()
) {
}
