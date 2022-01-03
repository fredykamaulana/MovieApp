package com.miniapp.core.data.source.remote

import com.miniapp.core.data.source.remote.network.AppApi

class RemoteDataSource(private val appApi: AppApi) {
    suspend fun getAppData(): List<String> = appApi.getAppData()
}