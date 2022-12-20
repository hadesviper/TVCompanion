package com.prtd.serial.domain.models


data class MultiResult(

    val page: Int,

    val results: List<Result>,

    val totalPages: Int,

    val totalResults: Int
) {
    data class Result(
        val id: Int,
        val firstAirDate: String?,
        val posterPath: String?,
        val releaseDate: String?,
        val type: String,
        val name:String,
        val vote: Double?,
        val year: String?,
        val autoComplete: String? = "$name ($year)"
    )
}