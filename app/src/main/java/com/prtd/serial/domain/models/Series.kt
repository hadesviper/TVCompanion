package com.prtd.serial.domain.models


data class Series(
    val backdrop_path : String?,
    val firstAirDate: String?,
    val genres: String,
    val lastAirDate: String?,
    val name: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val popularity: Double,
    val seasons: List<Season>,
    val status: String,
    val tagline: String,
    val videoID: String?="",
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
}