package com.miniapp.core.data.mapper

import com.miniapp.core.data.source.local.entity.MovieDetailEntity
import com.miniapp.core.data.source.remote.response.MovieDetailDto
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import java.lang.StringBuilder

val convertToMovieDetailDomainDataMapper = object : DataMapperAbstract<MovieDetailEntity?, MovieDetailDomainModel>() {
        override fun map(data: MovieDetailEntity?): MovieDetailDomainModel {
            return MovieDetailDomainModel(
                backdropPath = data?.backdropPath ?: "",
                revenue = data?.revenue ?: 0L,
                genresName = data?.genresName ?: listOf(),
                genresId = data?.genresId ?: listOf(),
                id = data?.id ?: 0,
                overview = data?.overview ?: "",
                originalTitle = data?.originalTitle ?: "",
                posterPath = data?.posterPath ?: "",
                spokenLanguagesCode = data?.spokenLanguagesCode ?: listOf(),
                spokenLanguagesName = data?.spokenLanguagesName ?: listOf(),
                productionCompaniesLogo = data?.productionCompaniesLogo ?: listOf(),
                productionCompaniesName = data?.productionCompaniesName ?: listOf(),
                productionCompaniesId = data?.productionCompaniesId ?: listOf(),
                productionCompaniesCountries = data?.productionCompaniesCountries ?: listOf(),
                releaseDate = data?.releaseDate ?: "",
                voteAverage = data?.voteAverage ?: 0.0,
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
            backdropPath = data.backdropPath ?: "",
            revenue = data.revenue ?: 0L,
            genresName = data.genres?.map { it?.name },
            genresId = data.genres?.map { it?.id },
            id = data.id ?: 0,
            overview = data.overview ?: "",
            originalTitle = data.originalTitle ?: "",
            posterPath = data.posterPath ?: "",
            spokenLanguagesCode = data.spokenLanguages?.map { it?.iso6391 },
            spokenLanguagesName = data.spokenLanguages?.map { it?.name },
            productionCompaniesLogo = data.productionCompanies?.map { it?.logoPath },
            productionCompaniesName = data.productionCompanies?.map { it?.name },
            productionCompaniesId = data.productionCompanies?.map { it?.id },
            productionCompaniesCountries = data.productionCompanies?.map { it?.originCountry },
            releaseDate = data.releaseDate ?: "",
            voteAverage = data.voteAverage ?: 0.0,
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