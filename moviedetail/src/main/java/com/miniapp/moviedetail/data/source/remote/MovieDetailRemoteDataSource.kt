package com.miniapp.moviedetail.data.source.remote

import com.miniapp.core.BuildConfig
import com.miniapp.core.data.source.remote.response.MovieDetailDto
import com.miniapp.core.data.source.remote.service.MovieDetailService
import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.data.source.remote.utils.SafeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class MovieDetailRemoteDataSource(private val service: MovieDetailService) : SafeApiCall() {

    suspend fun getMovieDetailById(
        dispatcher: CoroutineDispatcher,
        movieId: Int
    ): Flow<RemoteResult<MovieDetailDto>> {
        return safeApiCall(dispatcher) {
            service.getMovieDetailById(movieId, BuildConfig.API_KEY)
        }
    }
}