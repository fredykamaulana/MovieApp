package com.miniapp.moviedetail.domain.usecase

import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import com.miniapp.moviedetail.domain.repository.IMovieDetailRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailInteractor(private val repository: IMovieDetailRepository) : IMovieDetailUseCase {

    override fun getMovieDetailById(movieId: Int): Flow<ResourceState<MovieDetailDomainModel>> =
        repository.getMovieDetailById(movieId)

    override suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean) {
        repository.setMovieAsFavourite(movieId, fav)
    }
}