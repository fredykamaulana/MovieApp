package com.miniapp.moviefavourite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.miniapp.moviefavourite.domain.usecase.IMovieFavouriteUseCase
import kotlinx.coroutines.flow.retry

class MovieFavouriteViewModel(private val useCase: IMovieFavouriteUseCase) : ViewModel() {
    val getAllMovieFavourite = useCase.getAllFavouriteMovie().asLiveData()

    fun getAllMovieFavourite() {
        useCase.getAllFavouriteMovie().retry()
    }
}