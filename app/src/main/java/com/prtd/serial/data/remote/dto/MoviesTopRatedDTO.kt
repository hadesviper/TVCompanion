package com.prtd.serial.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.prtd.serial.domain.models.MoviesTopRated

data class MoviesTopRatedDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class Result(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}

fun MoviesTopRatedDTO.getMoviesTopRated(): MoviesTopRated {
    return MoviesTopRated(
        page = page,
        results = results.map {
            MoviesTopRated.Result(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate.split("-")[0],
                title = it.title,
                voteAverage = it.voteAverage,
                popularity =it.popularity
            )
        },
        totalPages = totalPages,
        totalResults = totalResults
    )
}