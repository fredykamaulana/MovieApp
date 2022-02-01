package com.miniapp.moviesearch.data.repository

import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.dispatcher.IDispatcherProvider
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.moviesearch.data.source.remote.MovieSearchRemoteDataSource
import com.miniapp.moviesearch.domain.repository.IMovieSearchRepository
import kotlinx.coroutines.flow.Flow

class MovieSearchRepositoryImpl(
    private val dispatcher: IDispatcherProvider,
    private val remoteDataSource: MovieSearchRemoteDataSource
) : IMovieSearchRepository {
    override suspend fun getMovieSearch(query: String): Flow<RemoteResult<List<MovieItemDomainModel>>> =
        remoteDataSource.getMovieSearch(query, dispatcher.io)
}