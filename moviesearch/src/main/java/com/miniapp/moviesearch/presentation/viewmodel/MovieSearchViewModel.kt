package com.miniapp.moviesearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.moviesearch.domain.usecase.IMovieSearchUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.last

class MovieSearchViewModel(private val useCase: IMovieSearchUseCase) : ViewModel() {

    private val _searchResult = MutableLiveData<RemoteResult<List<MovieItemDomainModel>>>()
    val searchResult: LiveData<RemoteResult<List<MovieItemDomainModel>>> get() = _searchResult

    @ObsoleteCoroutinesApi
    fun search(query: String) {
        viewModelScope.launch {
            delay(700)
            _searchResult.value = useCase.getMovieSearch(query).last()
        }
    }
}