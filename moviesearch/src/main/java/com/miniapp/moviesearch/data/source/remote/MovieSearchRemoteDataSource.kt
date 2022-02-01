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
                    originalLanguage = it.originalLanguage ?: "",
                    video = it.video ?: false,
                    title = it.title ?: "",
                    backdropPath = it.backdropPath ?: "",
                    overview = it.overview ?: "",
                    genreIds = it.genreIds ?: listOf(),
                    posterPath = it.posterPath ?: "",
                    releaseDate = it.releaseDate ?: "",
                    popularity = it.popularity ?: 0.0,
                    voteAverage = it.voteAverage ?: 0.0F,
                    id = it.id ?: 0,
                    adult = it.adult ?: false,
                    voteCount = it.voteCount ?: 0,
                    category = "search"
                )
            } ?: listOf()
        }
    }
}