package com.prtd.serial.domain.models

import com.prtd.serial.data.local.entities.EntitySeries


data class Series(
    val id: Int,
    val posterPath: String?,
    val backdrop_path: String?,
    val firstAirDate: String?,
    val genres: String?,
    val lastAirDate: String?,
    val name: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val popularity: Double,
    val seasons: List<Season>,
    val status: String,
    val tagline: String,
    val videoID: String?,
    val vote: Float,

    ) {
    data class Season(
        val airDate: String?,
        val episodeCount: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val posterPath: String?,
        val seasonNumber: Int
    )

    fun toEntitySeries(): EntitySeries {
        return EntitySeries(
            id = this.id,
            posterPath = this.posterPath,
            year = this.firstAirDate,
            name = this.name,
            vote = this.vote
        )
    }
}