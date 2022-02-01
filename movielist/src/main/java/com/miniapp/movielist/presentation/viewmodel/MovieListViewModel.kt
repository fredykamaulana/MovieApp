package com.miniapp.movielist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.movielist.domain.usecase.IMovieListUseCase
import kotlinx.coroutines.flow.retry

class MovieListViewModel(private val useCase: IMovieListUseCase): ViewModel() {
    val popularMovies = useCase.getPopularMovieList().asLiveData()
    val nowPlayingMovies = useCase.getNowPlayingMovieList().asLiveData()
    val topRatedMovies = useCase.getTopRatedMovieList().asLiveData()
    val upcomingMovies = useCase.getUpcomingMovieList().asLiveData()
    val getAllMovieList = useCase.getAllMovieList().asLiveData()

    fun getPopularMovies(){
        useCase.getPopularMovieList().retry()
    }

    fun getNowPlayingMovies(){
        useCase.getNowPlayingMovieList().retry()
    }

    fun getTopRatedMovies(){
        useCase.getTopRatedMovieList().retry()
    }

    fun getUpcomingMovies(){
        useCase.getUpcomingMovieList().retry()
    }

    fun getAllMovieList(){
        useCase.getAllMovieList().retry()
    }

    fun groupByCategory(list: List<MovieItemDomainModel>?): Collection<List<MovieItemDomainModel>>{
        return list?.groupBy { it.category }?.values ?: listOf()
    }
}