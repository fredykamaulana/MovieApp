package com.miniapp.core.data.source.remote.network

import retrofit2.http.GET

interface AppApi {
    @GET
    suspend fun getAppData(): List<String>
}