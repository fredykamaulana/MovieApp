package com.miniapp.moviesearch.domain.usecase

import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.moviesearch.domain.repository.IMovieSearchRepository
import kotlinx.coroutines.flow.Flow

class MovieSearchInteractor(private val repository: IMovieSearchRepository) : IMovieSearchUseCase {
    override suspend fun getMovieSearch(query: String): Flow<RemoteResult<List<MovieItemDomainModel>>> {
        return repository.getMovieSearch(query)
    }
}