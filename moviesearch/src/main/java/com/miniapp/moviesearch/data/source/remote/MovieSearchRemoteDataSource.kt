package com.miniapp.moviesearch.data.source.remote

import com.miniapp.core.BuildConfig
import com.miniapp.core.data.source.remote.service.MovieSearchService
import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.data.source.remote.utils.SafeApiCall
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class MovieSearchRemoteDataSource(private val service: MovieSearchService) : SafeApiCall() {
    suspend fun getMovieSearch(
        query: String,
        dispatcher: CoroutineDispatcher
    ): Flow<RemoteResult<List<MovieItemDomainModel>>> {
        return safeApiCall(dispatcher) {
            service.getSearchMovieList(BuildConfig.API_KEY, query).results?.map {
                MovieItemDomainModel(
                    originalTitle = it.originalTitle ?: "",
                    title = it.title ?: "",
                    posterPath = it.posterPath ?: "",
                    releaseDate = it.releaseDate ?: "",
                    voteAverage = it.voteAverage ?: 0.0F,
                    id = it.id ?: 0,
                    category = "search"
                )
            } ?: listOf()
        }
    }
}