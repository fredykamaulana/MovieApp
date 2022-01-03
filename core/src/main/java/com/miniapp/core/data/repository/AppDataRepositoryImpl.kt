package com.miniapp.core.data.repository

import com.miniapp.core.data.source.local.LocalDataSource
import com.miniapp.core.data.source.remote.RemoteDataSource
import com.miniapp.core.domain.repository.AppDataRepository

class AppDataRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): AppDataRepository {

    private val isLocalAvailable = false

    override suspend fun getAppData(): List<String> =
        if (isLocalAvailable) localDataSource.getAllAppData() else remoteDataSource.getAppData()

}