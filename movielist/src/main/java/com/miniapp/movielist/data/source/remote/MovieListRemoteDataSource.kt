package com.miniapp.movielist.data.source.remote

import com.miniapp.core.BuildConfig
import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.data.source.remote.utils.SafeApiCall
import com.miniapp.core.data.source.remote.response.MovieListDto
import com.miniapp.core.data.source.remote.service.MovieListService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class MovieListRemoteDataSource(private val service: MovieListService) : SafeApiCall() {
    suspend fun getPopularMovieList(dispatcher: CoroutineDispatcher): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
        return safeApiCall(dispatcher) {
            service.getPopularMovieList(BuildConfig.API_KEY).results ?: listOf()
        }
    }

    suspend fun getNowPlayingMovieList(dispatcher: CoroutineDispatcher): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
        return safeApiCall(dispatcher) {
            service.getNowPlayingMovieList(BuildConfig.API_KEY).results ?: listOf()
        }
    }

    suspend fun getTopRatedMovieList(dispatcher: CoroutineDispatcher): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
        return safeApiCall(dispatcher){
            service.getTopRatedMovieList(BuildConfig.API_KEY).results ?: listOf()
        }
    }

    suspend fun getUpcomingMovieList(dispatcher: CoroutineDispatcher): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
        return safeApiCall(dispatcher){
            service.getUpcomingMovieList(BuildConfig.API_KEY).results ?: listOf()
        }
    }
}