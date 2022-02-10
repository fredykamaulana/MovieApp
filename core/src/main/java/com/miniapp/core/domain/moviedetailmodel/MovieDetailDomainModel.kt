package com.miniapp.core.domain.moviedetailmodel

data class MovieDetailDomainModel(
    val backdropPath: String,
    val revenue: Long,
    val genresName: List<String?>,
    val genresId: List<Int?>,
    val id: Int,
    val overview: String,
    val originalTitle: String,
    val posterPath: String,
    val spokenLanguagesCode: List<String?>,
    val spokenLanguagesName: List<String?>,
    val productionCompaniesLogo: List<String?>,
    val productionCompaniesName: List<String?>,
    val productionCompaniesId: List<Int?>,
    val productionCompaniesCountries: List<String?>,
    val releaseDate: String,
    val voteAverage: Double,
    val adult: Boolean,
    val homepage: String,
    val status: String,
    val favourite: Boolean
)
