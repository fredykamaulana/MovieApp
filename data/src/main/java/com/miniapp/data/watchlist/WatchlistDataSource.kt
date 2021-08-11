package com.miniapp.data.watchlist

import com.miniapp.data.util.RemoteResult
import com.miniapp.domain.watchlist.GetWatchlist

interface WatchlistDataSource {
    suspend fun getWatchlist(): RemoteResult<GetWatchlist>
}