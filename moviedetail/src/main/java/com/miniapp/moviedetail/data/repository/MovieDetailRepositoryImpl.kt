package com.miniapp.moviedetail.data.repository

import com.miniapp.core.data.mapper.convertToMovieDetailDomainDataMapper
import com.miniapp.core.data.mapper.convertToMovieDetailEntityDataMapper
import com.miniapp.core.data.networkboundresource.NetworkBoundResource
import com.miniapp.core.data.source.remote.response.MovieDetailDto
import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.dispatcher.IDispatcherProvider
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import com.miniapp.moviedetail.data.source.local.MovieDetailLocalDataSource
import com.miniapp.moviedetail.data.source.remote.MovieDetailRemoteDataSource
import com.miniapp.moviedetail.domain.repository.IMovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieDetailRepositoryImpl(
    private val dispatcher: IDispatcherProvider,
    private val remoteDataSource: MovieDetailRemoteDataSource,
    private val localDataSource: MovieDetailLocalDataSource
) : IMovieDetailRepository {

    override fun getMovieDetailById(movieId: Int): Flow<ResourceState<MovieDetailDomainModel>> =
        object : NetworkBoundResource<MovieDetailDomainModel, MovieDetailDto>() {
            override suspend fun loadFromDB(): Flow<MovieDetailDomainModel> {
                return localDataSource.getMovieDetailById(movieId).map {
                    convertToMovieDetailDomainDataMapper.map(it)
                }
            }

            override fun shouldFetch(data: MovieDetailDomainModel?): Boolean {
                return data?.originalTitle?.isEmpty() ?: false || data == null
            }

            override suspend fun createCall(): Flow<RemoteResult<MovieDetailDto>> {
                return remoteDataSource.getMovieDetailById(dispatcher.io, movieId)
            }

            override suspend fun saveCallResult(data: MovieDetailDto) {
                return localDataSource.insertMovieDetailToDB(
                    convertToMovieDetailEntityDataMapper.map(data)
                )
            }
        }.asFlow()

    override suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean) {
        withContext(dispatcher.io) {
            localDataSource.setMovieAsFavourite(movieId, fav)
        }
    }
}