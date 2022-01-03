package com.miniapp.data.movielist

import com.miniapp.data.util.RemoteResult
import com.miniapp.domain.watchlist.GetMovieList

interface MovieListDataSource {
    suspend fun getMovieList(): RemoteResult<GetMovieList>
}