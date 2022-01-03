package com.miniapp.usecase.watchlist.watchlist

import com.miniapp.data.util.RemoteResult
import com.miniapp.data.watchlist.WatchlistRepository
import com.miniapp.domain.watchlist.GetWatchlist

class GetWatchlistUseCase(private val repository: WatchlistRepository) {
    suspend operator fun invoke(): RemoteResult<GetWatchlist> = repository.getWatchlist()
}