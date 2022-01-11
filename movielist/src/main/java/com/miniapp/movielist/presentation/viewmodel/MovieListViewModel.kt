package com.miniapp.movielist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.miniapp.movielist.domain.usecase.IMovieListUseCase
import kotlinx.coroutines.flow.retry

class MovieListViewModel(private val useCase: IMovieListUseCase): ViewModel() {
    val popularMovies = useCase.getPopularMovieList().asLiveData()
    val nowPlayingMovies = useCase.getNowPlayingMovieList().asLiveData()
    val topRatedMovies = useCase.getTopRatedMovieList().asLiveData()
    val upcomingMovies = useCase.getUpcomingMovieList().asLiveData()

    fun getPopularMovies(){
        useCase.getPopularMovieList().retry()
    }

    fun getNowPlayingMovies(){
        useCase.getPopularMovieList().retry()
    }

    fun getTopRatedMovies(){
        useCase.getPopularMovieList().retry()
    }

    fun getUpcomingMovies(){
        useCase.getPopularMovieList().retry()
    }
}