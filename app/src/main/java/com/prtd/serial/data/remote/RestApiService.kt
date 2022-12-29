package com.prtd.serial.data.remote

import com.prtd.serial.common.Constants.Api_Key
import com.prtd.serial.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApiService {
    @GET("search/multi?api_key=$Api_Key&language=en-US")
    suspend fun searchMulti(@Query("query") query:String,@Query("page") page:Int) : MultiResultsDTO

    @GET("search/movie?api_key=$Api_Key&language=en-US")
    suspend fun searchMovies(@Query("query") query:String,@Query("page") page:Int) : MoviesResultDTO

    @GET("search/tv?api_key=$Api_Key&language=en-US")
    suspend fun searchSeries(
        @Query("query") query: String,
        @Query("page") page: Int
    ): SeriesResultDTO

    @GET("movie/{id}?api_key=$Api_Key&language=en-US&append_to_response=videos")
    suspend fun getMovie(@Path("id") id: Int): MovieDTO

    @GET("tv/{id}?api_key=$Api_Key&language=en-US&&append_to_response=videos")
    suspend fun getSeries(@Path("id") id: Int): SeriesDTO

    @GET("movie/popular?api_key=$Api_Key&language=en-US")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesPopularDTO

    @GET("tv/popular?api_key=$Api_Key&language=en-US")
    suspend fun getPopularSeries(@Query("page") page: Int): SeriesPopularDTO

    @GET("movie/top_rated?api_key=$Api_Key&language=en-US")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MoviesTopRatedDTO

    @GET("tv/top_rated?api_key=$Api_Key&language=en-US")
    suspend fun getTopRatedSeries(@Query("page") page: Int): SeriesTopRatedDTO


    @GET("tv/{id}/similar?api_key=$Api_Key&language=en-US")
    suspend fun getSimilarSeries(@Path("id") id: Int, @Query("page") page: Int): SeriesResultDTO


    @GET("movie/{id}/similar?api_key=$Api_Key&language=en-US")
    suspend fun getSimilarMovies(@Path("id") id: Int, @Query("page") page: Int): MoviesResultDTO


}