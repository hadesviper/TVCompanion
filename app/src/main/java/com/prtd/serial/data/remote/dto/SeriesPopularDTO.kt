package com.prtd.serial.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.prtd.serial.domain.models.SeriesPopular

data class SeriesPopularDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
     class Result(
         @SerializedName("backdrop_path")
         val backdropPath: String,
         @SerializedName("first_air_date")
         val firstAirDate: String?,

         @SerializedName("id")
         val id: Int,
         @SerializedName("name")
         val name: String,
         @SerializedName("origin_country")
         val originCountry: List<String>,
         @SerializedName("original_language")
         val originalLanguage: String,
         @SerializedName("original_name")
         val originalName: String,
         @SerializedName("overview")
         val overview: String,
         @SerializedName("popularity")
         val popularity: Double,
         @SerializedName("poster_path")
         val posterPath: String?,
         @SerializedName("vote_average")
         val voteAverage: Float,
         @SerializedName("vote_count")
         val voteCount: Double
     )

    fun toSeriesPopular(): SeriesPopular {
        return SeriesPopular(
            page = page,
            results = results.map {
                SeriesPopular.Result(
                    firstAirDate = it.firstAirDate?.split("-")?.get(0) ?: "",
                    id = it.id,
                    name = it.name,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage
                )
            }, totalPages = 0, totalResults = 0

        )
    }
}

