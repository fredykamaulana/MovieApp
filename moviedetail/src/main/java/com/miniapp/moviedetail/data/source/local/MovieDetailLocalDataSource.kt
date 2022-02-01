package com.miniapp.moviedetail.data.source.local

import com.miniapp.core.data.source.local.dao.MovieDetailDao
import com.miniapp.core.data.source.local.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

class MovieDetailLocalDataSource(private val dao: MovieDetailDao) {

    fun getMovieDetailById(movieId: Int): Flow<MovieDetailEntity> {
        return dao.getMovieDetailWithId(movieId)
    }

    suspend fun insertMovieDetailToDB(detail: MovieDetailEntity) {
        dao.insertMovieDetail(detail)
    }

    suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean) {
        dao.setMovieAsFavourite(movieId, fav)
    }
}