package com.miniapp.moviefavourite.data.repository

import com.miniapp.core.data.mapper.convertToMovieDetailDomainDataMapper
import com.miniapp.core.data.networkboundresource.NetworkBoundResource
import com.miniapp.core.data.source.remote.response.MovieDetailDto
import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import com.miniapp.moviefavourite.data.source.MovieFavouriteLocalDataSource
import com.miniapp.moviefavourite.domain.repository.IMovieFavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieFavouriteRepositoryImpl(
    private val localDataSource: MovieFavouriteLocalDataSource
) : IMovieFavouriteRepository {
    override fun getAllFavouriteMovie(): Flow<ResourceState<List<MovieDetailDomainModel>>> =
        object : NetworkBoundResource<List<MovieDetailDomainModel>, List<MovieDetailDto>>() {
            override suspend fun loadFromDB(): Flow<List<MovieDetailDomainModel>> {
                return localDataSource.getAllMovieFavourite().map {
                    it.map { item ->
                        convertToMovieDetailDomainDataMapper.map(item)
                    }
                }
            }

            override fun shouldFetch(data: List<MovieDetailDomainModel>?): Boolean = false

            override suspend fun createCall(): Flow<RemoteResult<List<MovieDetailDto>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieDetailDto>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean) {
        localDataSource.setMovieAsFavourite(movieId, fav)
    }

}