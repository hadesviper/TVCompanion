package com.prtd.serial.domain.models



data class SeriesPopular(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class Result(
        val firstAirDate: String,
        val id: Int,
        val name: String,
        val popularity: Double,
        val posterPath: String?,
        val voteAverage: Float,
    )
}