package com.miniapp.moviesearch.domain.usecase

import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieSearchUseCase {
    suspend fun getMovieSearch(query: String): Flow<RemoteResult<List<MovieItemDomainModel>>>
}