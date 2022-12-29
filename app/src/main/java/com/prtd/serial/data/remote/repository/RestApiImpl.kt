package com.prtd.serial.data.remote.repository

import com.prtd.serial.data.remote.RestApiService
import com.prtd.serial.data.remote.dto.*
import com.prtd.serial.domain.repository.RestApiRepo
import javax.inject.Inject

class RestApiImpl @Inject constructor(
    private val restApiService: RestApiService
    ):RestApiRepo {



    override suspend fun searchMulti(query: String, page: Int): MultiResultsDTO {
        return restApiService.searchMulti(query, page)
    }

    override suspend fun searchSeries(query: String, page: Int): SeriesResultDTO {
        return restApiService.searchSeries(query, page)
    }

    override suspend fun searchMovies(query: String, page: Int): MoviesResultDTO {
        return restApiService.searchMovies(query, page)
    }

    override suspend fun getMovie(id: Int): MovieDTO {
        return restApiService.getMovie(id)
    }

    override suspend fun getSeries(id: Int): SeriesDTO {
        return restApiService.getSeries(id)
    }

    override suspend fun getPopularMovies(page: Int): MoviesPopularDTO {
        return restApiService.getPopularMovies(page)
    }

    override suspend fun getPopularSeries(page: Int): SeriesPopularDTO {
        return restApiService.getPopularSeries(page)
    }

    override suspend fun getTopRatedMovies(page: Int): MoviesTopRatedDTO {
        return restApiService.getTopRatedMovies(page)
    }

    override suspend fun getTopRatedSeries(page: Int): SeriesTopRatedDTO {
        return restApiService.getTopRatedSeries(page)
    }

    override suspend fun getSimilarSeries(id: Int, page: Int): SeriesResultDTO {
        return restApiService.getSimilarSeries(id, page)
    }

    override suspend fun getSimilarMovies(id: Int, page: Int): MoviesResultDTO {
        return restApiService.getSimilarMovies(id, page)
    }
}