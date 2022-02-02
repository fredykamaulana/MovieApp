package com.miniapp.moviefavourite.domain.usecase

import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import com.miniapp.moviefavourite.domain.repository.IMovieFavouriteRepository
import kotlinx.coroutines.flow.Flow

class MovieFavouriteInteractor(private val repository: IMovieFavouriteRepository) :
    IMovieFavouriteUseCase {
    override fun getAllFavouriteMovie(): Flow<ResourceState<List<MovieDetailDomainModel>>> {
        return repository.getAllFavouriteMovie()
    }

    override suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean) {
        return repository.setMovieAsFavourite(movieId, fav)
    }

}