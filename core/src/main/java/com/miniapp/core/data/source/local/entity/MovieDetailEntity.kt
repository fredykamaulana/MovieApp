package com.miniapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "imdb_id")
    val imdbId: String? = null,

    @ColumnInfo(name = "video")
    val video: Boolean? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "revenue")
    val revenue: Long? = null,

    @ColumnInfo(name = "genres_name")
    val genresName: List<String?>? = null,

    @ColumnInfo(name = "genres_id")
    val genresId: List<Int?>? = null,

    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,

    @ColumnInfo(name = "production_countries_code")
    val productionCountriesCode: List<String?>? = null,

    @ColumnInfo(name = "production_countries_name")
    val productionCountriesName: List<String?>? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int? = null,

    @ColumnInfo(name = "budget")
    val budget: Int? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,

    @ColumnInfo(name = "runtime")
    val runtime: Int? = null,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "spoken_languages_code")
    val spokenLanguagesCode: List<String?>? = null,

    @ColumnInfo(name = "spoken_languages_name")
    val spokenLanguagesName: List<String?>? = null,

    @ColumnInfo(name = "production_companies_logo")
    val productionCompaniesLogo: List<String?>? = null,

    @ColumnInfo(name = "production_companies_name")
    val productionCompaniesName: List<String?>? = null,

    @ColumnInfo(name = "production_companies_id")
    val productionCompaniesId: List<Int?>? = null,

    @ColumnInfo(name = "production_companies_countries")
    val productionCompaniesCountries: List<String?>? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,

    @ColumnInfo(name = "tagline")
    val tagline: String? = null,

    @ColumnInfo(name = "adult")
    val adult: Boolean? = null,

    @ColumnInfo(name = "homepage")
    val homepage: String? = null,

    @ColumnInfo(name = "status")
    val status: String? = null,

    @ColumnInfo(name = "favourite")
    val favourite: Boolean? = false
)
