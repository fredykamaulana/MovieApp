package com.miniapp.usecase.watchlist.movielist

import com.miniapp.data.movielist.MovieListRepository
import com.miniapp.data.util.RemoteResult
import com.miniapp.domain.watchlist.GetMovieList

class GetMovieListUseCase(private val repository: MovieListRepository) {
    suspend operator fun invoke(): RemoteResult<GetMovieList> = repository.getMovieList()
}