package com.miniapp.movielist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.movielist.domain.usecase.IMovieListUseCase
import kotlinx.coroutines.flow.retry
import timber.log.Timber

class MovieListViewModel(private val useCase: IMovieListUseCase): ViewModel() {
    val popularMovies = useCase.getPopularMovieList().asLiveData()
    val nowPlayingMovies = useCase.getNowPlayingMovieList().asLiveData()
    val topRatedMovies = useCase.getTopRatedMovieList().asLiveData()
    val upcomingMovies = useCase.getUpcomingMovieList().asLiveData()
    val getAllMovieList = useCase.getAllMovieList().asLiveData()

    private val _movieCategoryList = MutableLiveData<List<List<MovieItemDomainModel>>>()
    val movieCategoryList: LiveData<List<List<MovieItemDomainModel>>> get() = _movieCategoryList

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

    fun addMovieCategoryList(moviesCategoryList: List<MovieItemDomainModel>){
        val list = mutableListOf<List<MovieItemDomainModel>>()
        list.add(moviesCategoryList)

        _movieCategoryList.value = list
    }

    fun groupByCategory(list: List<MovieItemDomainModel>?): Collection<List<MovieItemDomainModel>>{
        return list?.groupBy { it.category }?.values ?: listOf()
    }
}