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

    override suspend fun getPopularMovies(): MoviesPopularDTO {
        return restApiService.getPopularMovies()
    }

    override suspend fun getPopularSeries(page: Int): SeriesPopularDTO {
        return restApiService.getPopularSeries(page)
    }

    override suspend fun getTopRatedMovies(): MoviesTopRatedDTO {
        return restApiService.getTopRatedMovies()
    }

    override suspend fun getTopRatedSeries(): SeriesTopRatedDTO {
        return restApiService.getTopRatedSeries()
    }

    override suspend fun getSimilarSeries(id: Int): SeriesResultDTO {
        return restApiService.getSimilarSeries(id)
    }

    override suspend fun getSimilarMovies(id: Int): MoviesResultDTO {
        return restApiService.getSimilarMovies(id)    }
}