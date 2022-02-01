package com.miniapp.moviedetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.miniapp.moviedetail.domain.usecase.IMovieDetailUseCase
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieId: Int, private val useCase: IMovieDetailUseCase) :
    ViewModel() {

    val movieDetail = useCase.getMovieDetailById(movieId).asLiveData()


    fun getMovieDetailById() {
        useCase.getMovieDetailById(movieId).retry()
    }

    fun setMovieAsFavourite(movieId: Int, fav: Boolean) {
        viewModelScope.launch {
            useCase.setMovieAsFavourite(movieId, fav)
        }
    }
}