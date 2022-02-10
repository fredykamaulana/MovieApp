package com.miniapp.core.domain.movielistmodel

data class MovieItemDomainModel(
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Float,
    val id: Int,
    val category: String
)
