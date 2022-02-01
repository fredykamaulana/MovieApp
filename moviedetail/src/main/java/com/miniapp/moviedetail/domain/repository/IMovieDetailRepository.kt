package com.miniapp.moviedetail.domain.repository

import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieDetailRepository {

    fun getMovieDetailById(movieId: Int): Flow<ResourceState<MovieDetailDomainModel>>

    suspend fun setMovieAsFavourite(movieId: Int, fav: Boolean)
}