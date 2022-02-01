package com.miniapp.movielist.domain.usecase

import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.movielist.domain.repository.IMovieListRepository
import kotlinx.coroutines.flow.Flow

class MovieListInteractor(private val repository: IMovieListRepository) : IMovieListUseCase {
    override fun getPopularMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        repository.getPopularMovieList()

    override fun getNowPlayingMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        repository.getNowPlayingMovieList()

    override fun getTopRatedMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        repository.getTopRatedMovieList()

    override fun getUpcomingMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        repository.getUpcomingMovieList()

    override fun getAllMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        repository.getAllMovieList()
}