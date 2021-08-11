package com.miniapp.app.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miniapp.data.util.RemoteResult
import com.miniapp.domain.watchlist.GetWatchlist
import com.miniapp.usecase.watchlist.WatchlistUseCases
import kotlinx.coroutines.launch

class WatchListViewModel(private val useCases: WatchlistUseCases) : ViewModel() {

    private val _getWatchlist = MutableLiveData<RemoteResult<GetWatchlist>>()
    val getWatchlist: LiveData<RemoteResult<GetWatchlist>> get() = _getWatchlist

    fun getWatchlist() {
        _getWatchlist.value = RemoteResult.Loading
        viewModelScope.launch {
            _getWatchlist.value = useCases.getWatchlistUseCases()
        }
    }

}