package com.miniapp.core.data.mapper

import com.miniapp.core.data.source.local.entity.MovieItemEntity
import com.miniapp.core.data.source.remote.response.MovieListDto
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel

val convertToMovieListDomainDataMapper = object : DataMapperAbstract<MovieItemEntity, MovieItemDomainModel>() {
    override fun map(data: MovieItemEntity): MovieItemDomainModel {
        return MovieItemDomainModel(
            originalTitle = data.originalTitle ?: "",
            title = data.title ?: "",
            posterPath = data.posterPath ?: "",
            releaseDate = data.releaseDate ?: "",
            voteAverage = data.voteAverage ?: 0f,
            id = data.id ?: 0,
            category = data.category ?: ""
        )
    }
}

val convertToMovieListEntityDataMapper = object : DataMapperAbstract<MovieListDto.MovieItemDto, MovieItemEntity>() {
    override fun map(data: MovieListDto.MovieItemDto): MovieItemEntity {
        return MovieItemEntity(
            originalTitle = data.originalTitle ?: "",
            title = data.title ?: "",
            posterPath = data.posterPath ?: "",
            releaseDate = data.releaseDate ?: "",
            voteAverage = data.voteAverage ?: 0f,
            id = data.id ?: 0,
            category = ""
        )
    }
}