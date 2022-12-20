package com.prtd.serial.domain.repository

import com.prtd.serial.data.remote.dto.*


interface RestApiRepo {

    suspend fun searchMulti(query:String,page:Int) : MultiResultsDTO
    suspend fun searchSeries(query:String,page:Int) : SeriesResultDTO
    suspend fun searchMovies(query:String,page:Int) : MoviesResultDTO
    suspend fun getMovie(id:Int)   : MovieDTO
    suspend fun getSeries(id:Int)  : SeriesDTO
    suspend fun getPopularMovies() : MoviesPopularDTO
    suspend fun getPopularSeries(page:Int) : SeriesPopularDTO
    suspend fun getTopRatedMovies() : MoviesTopRatedDTO
    suspend fun getTopRatedSeries() : SeriesTopRatedDTO
    suspend fun getSimilarSeries(id:Int) : SeriesResultDTO
    suspend fun getSimilarMovies(id:Int) : MoviesResultDTO



}