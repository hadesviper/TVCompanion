package com.prtd.serial.data.remote.dto


import android.util.Log
import com.google.gson.annotations.SerializedName
import com.prtd.serial.common.Constants
import com.prtd.serial.domain.models.MultiResult

data class MultiResultsDTO(
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
        val adult: Boolean?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("first_air_date")
        val firstAirDate: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int?>?,
        @SerializedName("id")
        val id: Int,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("name")
        val name: String?,
        @SerializedName("origin_country")
        val originCountry: List<String?>?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        @SerializedName("original_name")
        val originalName: String?,
        @SerializedName("original_title")
        val originalTitle: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("popularity")
        val popularity: Double?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean?,
        @SerializedName("vote_average")
        val voteAverage: Double?,
        @SerializedName("vote_count")
        val voteCount: Int?
    )
}
fun MultiResultsDTO.getMultiResult(): MultiResult {

    Log.i("results", "getMultiResult: $results")

    val filtered=results.filter {
            result ->
        result.mediaType== Constants.Type_Movie || result.mediaType==Constants.Type_Series
    }
    Log.i("filtered", "getMultiResult: $filtered")

    return MultiResult(
        page = page,
        results = filtered.map {
            MultiResult.Result(
                firstAirDate = it.firstAirDate?.split("-")?.get(0),
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate?.split("-")?.get(0),
                type = it.mediaType,
                name = if (it.name.isNullOrBlank()) it.title else it.name,
                year = if (it.firstAirDate.isNullOrBlank())
                    it.releaseDate?.split("-")?.get(0)
                else
                    it.firstAirDate.split("-")[0],
                vote = it.voteAverage
            )
        }, totalPages = totalPages, totalResults = totalResults

    )
}

