package com.miniapp.movielist.domain.usecase

import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieListUseCase {
    fun getPopularMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>>

    fun getNowPlayingMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>>

    fun getTopRatedMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>>

    fun getUpcomingMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>>

    fun getAllMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>>
}