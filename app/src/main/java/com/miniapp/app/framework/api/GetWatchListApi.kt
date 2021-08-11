package com.miniapp.app.framework.api

import com.miniapp.app.framework.model.response.GetWatchlistResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetWatchListApi {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getWatchList(@Query("limit") limit: Int,
                             @Query("tsym") tsym: String): GetWatchlistResponse
}