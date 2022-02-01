package com.miniapp.core.data.source.remote.service

import com.miniapp.core.data.source.remote.response.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchService {

    //https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&query=Avengers

    @GET("search/movie")
    suspend fun getSearchMovieList(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieListDto
}