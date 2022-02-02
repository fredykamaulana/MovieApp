package com.miniapp.moviefavourite.domain.repository

import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieFavouriteRepository {

    fun getAllFavouriteMovie(): Flow<ResourceState<List<MovieDetailDomainModel>>>

    suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean)
}