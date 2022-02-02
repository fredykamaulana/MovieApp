package com.miniapp.moviefavourite.data.source

import com.miniapp.core.data.source.local.dao.MovieFavouriteDao
import com.miniapp.core.data.source.local.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

class MovieFavouriteLocalDataSource(private val dao: MovieFavouriteDao) {

    fun getAllMovieFavourite(): Flow<List<MovieDetailEntity>> {
        return dao.getAllMovieFavourite()
    }

    suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean) {
        dao.setMovieAsFavourite(movieId, fav)
    }
}