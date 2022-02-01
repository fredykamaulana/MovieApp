package com.miniapp.moviesearch.domain.repository

import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieSearchRepository {
    suspend fun getMovieSearch(query: String): Flow<RemoteResult<List<MovieItemDomainModel>>>
}