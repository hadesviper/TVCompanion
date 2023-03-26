package com.prtd.serial.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.prtd.serial.common.HelperMethods.roundToDecimalPlaces
import com.prtd.serial.domain.models.Series

data class SeriesDTO(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("created_by")
    val createdBy: List<CreatedBy> = emptyList(),
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int> = emptyList(),
    @SerializedName("first_air_date")
    val firstAirDate: String? = "",
    @SerializedName("genres")
    val genres: List<Genre> = emptyList(),
    @SerializedName("homepage")
    val homepage: String = "",
    @SerializedName("id")
    val id: Int,
    @SerializedName("in_production")
    val inProduction: Boolean,
    @SerializedName("languages")
    val languages: List<String> = emptyList(),
    @SerializedName("last_air_date")
    val lastAirDate: String? = "",
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir?,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("networks")
    val networks: List<Network> = emptyList(),
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("origin_country")
    val originCountry: List<String> = emptyList(),
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany> = emptyList(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry> = emptyList(),
    @SerializedName("seasons")
    val seasons: List<Season> = emptyList(),
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("videos")
    val videos: Videos = Videos(),
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {

    data class CreatedBy(
        @SerializedName("credit_id")
        val creditId: String = "",
        @SerializedName("gender")
        val gender: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("profile_path")
        val profilePath: String = ""
    )

    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String = ""
    )

    data class LastEpisodeToAir(
        @SerializedName("air_date")
        val airDate: String = "",
        @SerializedName("episode_number")
        val episodeNumber: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("overview")
        val overview: String = "",
        @SerializedName("production_code")
        val productionCode: String = "",
        @SerializedName("runtime")
        val runtime: Int,
        @SerializedName("season_number")
        val seasonNumber: Int,
        @SerializedName("show_id")
        val showId: Int,
        @SerializedName("still_path")
        val stillPath: String = "",
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )

    data class Network(
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("origin_country")
        val originCountry: String = ""
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

    data class Season(
        @SerializedName("air_date")
        val airDate: String? = "",
        @SerializedName("episode_count")
        val episodeCount: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("overview")
        val overview: String = "",
        @SerializedName("poster_path")
        val posterPath: String? = "",
        @SerializedName("season_number")
        val seasonNumber: Int
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

    fun toSeries(): Series {
        return Series(
            id = id,
            posterPath = posterPath.toString(),
            backdrop_path = backdropPath,
            firstAirDate = firstAirDate?.split("-")?.get(0),
            genres = genres.joinToString { it.name },
            lastAirDate = lastAirDate?.split("-")?.get(0),
            name = name,
            numberOfEpisodes = numberOfEpisodes,
            numberOfSeasons = numberOfSeasons,
            overview = overview,
            popularity = popularity,
            seasons = seasons.map {
                Series.Season(
                    airDate = it.airDate?.split("-")?.get(0),
                    episodeCount = it.episodeCount,
                    id = it.id,
                    name = it.name,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    seasonNumber = it.seasonNumber
                )
            },
            status = status,
            tagline = tagline,
            videoID = if (videos.results.isNotEmpty()) videos.results[0].key else "",
            vote = roundToDecimalPlaces(voteAverage.toFloat(), 1)
        )
    }
}

