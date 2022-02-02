package com.miniapp.moviefavourite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.miniapp.moviefavourite.domain.usecase.IMovieFavouriteUseCase
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

class MovieFavouriteViewModel(private val useCase: IMovieFavouriteUseCase) : ViewModel() {
    val getAllMovieFavourite = useCase.getAllFavouriteMovie().asLiveData()

    fun getAllMovieFavourite() {
        useCase.getAllFavouriteMovie().retry()
    }

    fun setMovieAsFavourite(movieId: Int, fav: Boolean) {
        viewModelScope.launch {
            useCase.setMovieAsFavourite(movieId, fav)
        }
    }
}