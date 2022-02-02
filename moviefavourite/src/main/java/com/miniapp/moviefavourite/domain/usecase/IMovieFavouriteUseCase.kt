package com.miniapp.moviefavourite.domain.usecase

import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieFavouriteUseCase {

    fun getAllFavouriteMovie(): Flow<ResourceState<List<MovieDetailDomainModel>>>

    suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean)
}