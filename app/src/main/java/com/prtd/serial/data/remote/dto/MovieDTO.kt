package com.prtd.serial.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.prtd.serial.common.HelperMethods.roundToDecimalPlaces
import com.prtd.serial.domain.models.Movie

data class MovieDTO(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String = "",
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("revenue")
    val revenue: Double = 0.0,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("videos")
    val videos: Videos = Videos(),
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Double = 0.0
) {
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String = ""
    )

    data class ProductionCompany(
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("origin_country")
        val originCountry: String = ""
    )

    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        val iso31661: String = "",
        @SerializedName("name")
        val name: String = ""
    )

    data class SpokenLanguage(
        @SerializedName("english_name")
        val englishName: String = "",
        @SerializedName("iso_639_1")
        val iso6391: String = "",
        @SerializedName("name")
        val name: String = ""
    )

    data class Videos(
        @SerializedName("results")
        val results: List<Result> = emptyList()
    ) {
        data class Result(
            @SerializedName("id")
            val id: String = "",
            @SerializedName("iso_3166_1")
            val iso31661: String = "",
            @SerializedName("iso_639_1")
            val iso6391: String = "",
            @SerializedName("key")
            val key: String = "",
            @SerializedName("name")
            val name: String = "",
            @SerializedName("official")
            val official: Boolean = false,
            @SerializedName("published_at")
            val publishedAt: String = "",
            @SerializedName("site")
            val site: String = "",
            @SerializedName("size")
            val size: Int = 0,
            @SerializedName("type")
            val type: String = ""
        )
    }

    fun toMovie(): Movie {
        return Movie(
            id = id,
            posterPath = posterPath,
            backdrop_path = backdropPath,
            genre = genres.joinToString { it.name },
            overview = overview,
            popularity = popularity,
            title = title,
            tagline = tagline,
            videoID = videos.results[0].key,
            year = releaseDate.split("-")[0],
            vote = roundToDecimalPlaces(voteAverage.toFloat(), 1),
            duration = runtime
        )
    }
}

