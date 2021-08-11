package com.miniapp.data.watchlist

import com.miniapp.data.util.RemoteResult
import com.miniapp.domain.watchlist.GetWatchlist

class WatchlistRepository(private val dataSource: WatchlistDataSource) {
    suspend fun getWatchlist(): RemoteResult<GetWatchlist> = dataSource.getWatchlist()
}