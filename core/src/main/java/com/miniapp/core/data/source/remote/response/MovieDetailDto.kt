package com.miniapp.core.data.source.remote.response

import com.squareup.moshi.Json

data class MovieDetailDto(

    @field:Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @field:Json(name = "revenue")
    val revenue: Long? = null,

    @field:Json(name = "genres")
    val genres: List<GenresItem?>? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "overview")
    val overview: String? = null,

    @field:Json(name = "original_title")
    val originalTitle: String? = null,

    @field:Json(name = "poster_path")
    val posterPath: String? = null,

    @field:Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem?>? = null,

    @field:Json(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,

    @field:Json(name = "release_date")
    val releaseDate: String? = null,

    @field:Json(name = "vote_average")
    val voteAverage: Double? = null,

    @field:Json(name = "adult")
    val adult: Boolean? = null,

    @field:Json(name = "homepage")
    val homepage: String? = null,

    @field:Json(name = "status")
    val status: String? = null
)

data class ProductionCompaniesItem(

    @field:Json(name = "logo_path")
    val logoPath: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "origin_country")
    val originCountry: String? = null
)

data class GenresItem(

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "id")
    val id: Int? = null
)

data class SpokenLanguagesItem(

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "iso_639_1")
    val iso6391: String? = null
)
