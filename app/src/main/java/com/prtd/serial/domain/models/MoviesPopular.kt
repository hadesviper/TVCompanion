package com.prtd.serial.domain.models


data class MoviesPopular(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class Result(
        val id: Int,
        val posterPath: String?,
        val releaseDate: String,
        val title: String,
        val voteAverage: Float,
        val popularity: Double,
    )
}