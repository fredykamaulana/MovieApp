package com.miniapp.data.movielist

import com.miniapp.data.util.RemoteResult
import com.miniapp.domain.watchlist.GetMovieList

class MovieListRepository(private val dataSource: MovieListDataSource) {
    suspend fun getMovieList(): RemoteResult<GetMovieList> = dataSource.getMovieList()
}