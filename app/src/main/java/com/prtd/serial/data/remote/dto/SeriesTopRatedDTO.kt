package com.prtd.serial.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.prtd.serial.domain.models.SeriesTopRated

data class SeriesTopRatedDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TopRated>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalTopRateds: Int
) {
    data class TopRated(
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("first_air_date")
        val firstAirDate: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
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

    fun toSeriesTopRated(): SeriesTopRated {
        return SeriesTopRated(
            page = page,
            results = results.map {
                SeriesTopRated.Result(
                    firstAirDate = it.firstAirDate?.split("-")?.get(0) ?: "",
                    id = it.id,
                    name = it.name,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage
                )
            }, totalPages = totalPages, totalResults = totalTopRateds

        )
    }
}
