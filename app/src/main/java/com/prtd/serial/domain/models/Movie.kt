package com.prtd.serial.domain.models


data class Movie(
    val backdrop_path:  String?,
    val genre:          String,
    val overview:       String,
    val popularity:     Double,
    val title:          String,
    val tagline:        String,
    val videoID:        String?,
    val year:           String,
    val vote:           Float,
    val duration:       Int
)
