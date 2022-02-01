package com.miniapp.movielist.data.repository

import com.miniapp.core.data.networkboundresource.NetworkBoundResource
import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.dispatcher.IDispatcherProvider
import com.miniapp.core.data.mapper.convertToMovieListDomainDataMapper
import com.miniapp.movielist.data.source.local.MovieListLocalDataSource
import com.miniapp.movielist.data.source.remote.MovieListRemoteDataSource
import com.miniapp.core.data.source.remote.response.MovieListDto
import com.miniapp.core.data.mapper.convertToMovieListEntityDataMapper
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.movielist.domain.repository.IMovieListRepository
import kotlinx.coroutines.flow.*

class MovieListRepositoryImpl(
    private val dispatcher: IDispatcherProvider,
    private val remoteDataSource: MovieListRemoteDataSource,
    private val localDataSource: MovieListLocalDataSource
) : IMovieListRepository {
    override fun getPopularMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        object :
            NetworkBoundResource<List<MovieItemDomainModel>, List<MovieListDto.MovieItemDto>>() {

            override suspend fun loadFromDB(): Flow<List<MovieItemDomainModel>> {
                return localDataSource.getPopularMovieList().map { list ->
                    list.map { convertToMovieListDomainDataMapper.map(it) }
                }
            }

            override fun shouldFetch(data: List<MovieItemDomainModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
                return remoteDataSource.getPopularMovieList(dispatcher.io)
            }

            override suspend fun saveCallResult(data: List<MovieListDto.MovieItemDto>) {
                localDataSource.insertMovieListToDB(data.map {
                    convertToMovieListEntityDataMapper.map(it).copy(category = "popular")
                })
            }
        }.asFlow()

    override fun getNowPlayingMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        object :
            NetworkBoundResource<List<MovieItemDomainModel>, List<MovieListDto.MovieItemDto>>() {

            override suspend fun loadFromDB(): Flow<List<MovieItemDomainModel>> {
                return localDataSource.getNowPlayingMovieList().map { list ->
                    list.map { convertToMovieListDomainDataMapper.map(it) }
                }
            }

            override fun shouldFetch(data: List<MovieItemDomainModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
                return remoteDataSource.getNowPlayingMovieList(dispatcher.io)
            }

            override suspend fun saveCallResult(data: List<MovieListDto.MovieItemDto>) {
                localDataSource.insertMovieListToDB(data.map {
                    convertToMovieListEntityDataMapper.map(it).copy(category = "now_playing")
                })
            }
        }.asFlow()

    override fun getTopRatedMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        object :
            NetworkBoundResource<List<MovieItemDomainModel>, List<MovieListDto.MovieItemDto>>() {

            override suspend fun loadFromDB(): Flow<List<MovieItemDomainModel>> {
                return localDataSource.getTopRatedMovieList().map { list ->
                    list.map { convertToMovieListDomainDataMapper.map(it) }
                }
            }

            override fun shouldFetch(data: List<MovieItemDomainModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
                return remoteDataSource.getTopRatedMovieList(dispatcher.io)
            }

            override suspend fun saveCallResult(data: List<MovieListDto.MovieItemDto>) {
                localDataSource.insertMovieListToDB(data.map {
                    convertToMovieListEntityDataMapper.map(it).copy(category = "top_rated")
                })
            }
        }.asFlow()

    override fun getUpcomingMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        object :
            NetworkBoundResource<List<MovieItemDomainModel>, List<MovieListDto.MovieItemDto>>() {

            override suspend fun loadFromDB(): Flow<List<MovieItemDomainModel>> {
                return localDataSource.getUpcomingMovieList().map { list ->
                    list.map { convertToMovieListDomainDataMapper.map(it) }
                }
            }

            override fun shouldFetch(data: List<MovieItemDomainModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
                return remoteDataSource.getUpcomingMovieList(dispatcher.io)
            }

            override suspend fun saveCallResult(data: List<MovieListDto.MovieItemDto>) {
                localDataSource.insertMovieListToDB(data.map {
                    convertToMovieListEntityDataMapper.map(it).copy(category = "upcoming")
                })
            }
        }.asFlow()

    override fun getAllMovieList(): Flow<ResourceState<List<MovieItemDomainModel>>> =
        object :
            NetworkBoundResource<List<MovieItemDomainModel>, List<MovieListDto.MovieItemDto>>() {

            override suspend fun loadFromDB(): Flow<List<MovieItemDomainModel>> {
                return localDataSource.getAllMovieList().map { list ->
                    list.map { convertToMovieListDomainDataMapper.map(it) }
                }
            }

            override fun shouldFetch(data: List<MovieItemDomainModel>?): Boolean {
                return false
            }

            override suspend fun createCall(): Flow<RemoteResult<List<MovieListDto.MovieItemDto>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieListDto.MovieItemDto>) {
                TODO("Not yet implemented")
            }
        }.asFlow()
}