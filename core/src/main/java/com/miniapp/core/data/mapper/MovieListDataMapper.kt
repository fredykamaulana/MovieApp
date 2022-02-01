package com.miniapp.core.data.mapper

import com.miniapp.core.data.source.local.entity.MovieItemEntity
import com.miniapp.core.data.source.remote.response.MovieListDto
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel

val convertToMovieListDomainDataMapper = object : DataMapperAbstract<MovieItemEntity, MovieItemDomainModel>() {
    override fun map(data: MovieItemEntity): MovieItemDomainModel {
        return MovieItemDomainModel(
            overview = data.overview ?: "",
            originalLanguage = data.originalLanguage ?: "",
            originalTitle = data.originalTitle ?: "",
            video = data.video ?: false,
            title = data.title ?: "",
            genreIds = data.genreIds ?: listOf(),
            posterPath = data.posterPath ?: "",
            backdropPath = data.backdropPath ?: "",
            releaseDate = data.releaseDate ?: "",
            popularity = data.popularity ?: 0.0,
            voteAverage = data.voteAverage ?: 0f,
            id = data.id ?: 0,
            adult = data.adult ?: false,
            voteCount = data.voteCount ?: 0,
            category = data.category ?: ""
        )
    }
}

val convertToMovieListEntityDataMapper = object : DataMapperAbstract<MovieListDto.MovieItemDto, MovieItemEntity>() {
    override fun map(data: MovieListDto.MovieItemDto): MovieItemEntity {
        return MovieItemEntity(
            overview = data.overview ?: "",
            originalLanguage = data.originalLanguage ?: "",
            originalTitle = data.originalTitle ?: "",
            video = data.video ?: false,
            title = data.title ?: "",
            genreIds = data.genreIds ?: listOf(0),
            posterPath = data.posterPath ?: "",
            backdropPath = data.backdropPath ?: "",
            releaseDate = data.releaseDate ?: "",
            popularity = data.popularity ?: 0.0,
            voteAverage = data.voteAverage ?: 0f,
            id = data.id ?: 0,
            adult = data.adult ?: false,
            voteCount = data.voteCount ?: 0,
            category = ""
        )
    }
}