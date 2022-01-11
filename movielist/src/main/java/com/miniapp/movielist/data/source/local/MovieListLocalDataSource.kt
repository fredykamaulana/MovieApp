package com.miniapp.movielist.data.source.local

import com.miniapp.core.data.source.local.dao.MovieItemDao
import com.miniapp.core.data.source.local.entity.MovieItemEntity
import kotlinx.coroutines.flow.Flow

class MovieListLocalDataSource(private val dao: MovieItemDao) {
    suspend fun insertMovieListToDB(movies: List<MovieItemEntity>) {
        dao.insertMovieItem(movies)
    }

    fun getAllMovieList(): Flow<List<MovieItemEntity>> {
        return dao.getAllMovieList()
    }

    fun getPopularMovieList(): Flow<List<MovieItemEntity>> {
        return dao.getPopularMovieList()
    }

    fun getNowPlayingMovieList(): Flow<List<MovieItemEntity>> {
        return dao.getNowPlayingMovieList()
    }

    fun getTopRatedMovieList(): Flow<List<MovieItemEntity>> {
        return dao.getTopRatedMovieList()
    }

    fun getUpcomingMovieList(): Flow<List<MovieItemEntity>> {
        return dao.getUpcomingMovieList()
    }
}
