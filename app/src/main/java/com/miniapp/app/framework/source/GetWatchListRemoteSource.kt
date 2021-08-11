package com.miniapp.app.framework.source

import com.miniapp.app.framework.api.GetWatchListApi
import com.miniapp.app.framework.util.ApiSource
import com.miniapp.data.util.RemoteResult
import com.miniapp.data.watchlist.WatchlistDataSource
import com.miniapp.domain.watchlist.GetWatchlist

class GetWatchListRemoteSource(private val api: GetWatchListApi): ApiSource(), WatchlistDataSource {
    override suspend fun getWatchlist(): RemoteResult<GetWatchlist> {
        return getApiResult { api.getWatchList(10, "USD").map() }
    }
}