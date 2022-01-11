package com.miniapp.core.data.source.remote.service

import com.miniapp.core.data.source.remote.response.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListService {
    @GET("movie/popular")
    suspend fun getPopularMovieList(@Query("api_key") apiKey: String): MovieListDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovieList(@Query("api_key") apiKey: String): MovieListDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovieList(@Query("api_key") apiKey: String): MovieListDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovieList(@Query("api_key") apiKey: String): MovieListDto
}