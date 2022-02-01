package com.miniapp.core.data.mapper

import com.miniapp.core.data.source.local.entity.MovieDetailEntity
import com.miniapp.core.data.source.remote.response.MovieDetailDto
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import java.lang.StringBuilder

val convertToMovieDetailDomainDataMapper = object : DataMapperAbstract<MovieDetailEntity?, MovieDetailDomainModel>() {
        override fun map(data: MovieDetailEntity?): MovieDetailDomainModel {
            return MovieDetailDomainModel(
                originalLanguage = data?.originalLanguage ?: "",
                imdbId = data?.imdbId ?: "",
                video = data?.video ?: false,
                title = data?.title ?: "",
                backdropPath = data?.backdropPath ?: "",
                revenue = data?.revenue ?: 0,
                genresName = data?.genresName ?: listOf(),
                genresId = data?.genresId ?: listOf(),
                popularity = data?.popularity ?: 0.0,
                productionCountriesCode = data?.productionCountriesCode ?: listOf(),
                productionCountriesName = data?.productionCountriesName ?: listOf(),
                id = data?.id ?: 0,
                voteCount = data?.voteCount ?: 0,
                budget = data?.budget ?: 0,
                overview = data?.overview ?: "",
                originalTitle = data?.originalTitle ?: "",
                runtime = data?.runtime ?: 0,
                posterPath = data?.posterPath ?: "",
                spokenLanguagesCode = data?.spokenLanguagesCode ?: listOf(),
                spokenLanguagesName = data?.spokenLanguagesName ?: listOf(),
                productionCompaniesLogo = data?.productionCompaniesLogo ?: listOf(),
                productionCompaniesName = data?.productionCompaniesName ?: listOf(),
                productionCompaniesId = data?.productionCompaniesId ?: listOf(),
                productionCompaniesCountries = data?.productionCompaniesCountries ?: listOf(),
                releaseDate = data?.releaseDate ?: "",
                voteAverage = data?.voteAverage ?: 0.0,
                tagline = data?.tagline ?: "",
                adult = data?.adult ?: false,
                homepage = data?.homepage ?: "",
                status = data?.status ?: "",
                favourite = data?.favourite ?: false
            )
        }
    }

val convertToMovieDetailEntityDataMapper = object : DataMapperAbstract<MovieDetailDto, MovieDetailEntity>() {
    override fun map(data: MovieDetailDto): MovieDetailEntity {
        return MovieDetailEntity(
            originalLanguage = data.originalLanguage ?: "",
            imdbId = data.imdbId ?: "",
            video = data.video ?: false,
            title = data.title ?: "",
            backdropPath = data.backdropPath ?: "",
            revenue = data.revenue ?: 0,
            genresName = data.genres?.map { it?.name },
            genresId = data.genres?.map { it?.id },
            popularity = data.popularity ?: 0.0,
            productionCountriesCode = data.productionCountries?.map { it?.iso31661 },
            productionCountriesName = data.productionCountries?.map { it?.name },
            id = data.id ?: 0,
            voteCount = data.voteCount ?: 0,
            budget = data.budget ?: 0,
            overview = data.overview ?: "",
            originalTitle = data.originalTitle ?: "",
            runtime = data.runtime ?: 0,
            posterPath = data.posterPath ?: "",
            spokenLanguagesCode = data.spokenLanguages?.map { it?.iso6391 },
            spokenLanguagesName = data.spokenLanguages?.map { it?.name },
            productionCompaniesLogo = data.productionCompanies?.map { it?.logoPath },
            productionCompaniesName = data.productionCompanies?.map { it?.name },
            productionCompaniesId = data.productionCompanies?.map { it?.id },
            productionCompaniesCountries = data.productionCompanies?.map { it?.originCountry },
            releaseDate = data.releaseDate ?: "",
            voteAverage = data.voteAverage ?: 0.0,
            tagline = data.tagline ?: "",
            adult = data.adult ?: false,
            homepage = data.homepage ?: "",
            status = data.status ?: "",
            favourite = false
        )
    }
}

fun List<String?>.toSingleString(): String{
    val str = StringBuilder()
    this.forEachIndexed { i, s ->
        if (this.size == 1 || i == lastIndex) str.append(s)
        else str.append(" $s,")
    }

    return str.toString()
}