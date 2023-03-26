package com.prtd.serial.domain.models

import com.prtd.serial.data.local.entities.EntityMovie


data class Movie(
    val id: Int,
    val posterPath: String?,
    val backdrop_path: String,
    val genre: String,
    val overview: String,
    val popularity: Double,
    val title: String,
    val tagline: String,
    val videoID: String,
    val year: String,
    val vote: Float,
    val duration: Int
) {
    fun toEntityMovie(): EntityMovie {
        return EntityMovie(
            id = this.id,
            posterPath = this.posterPath.toString(),
            year = this.year,
            title = this.title,
            vote = this.vote
        )
    }
}
