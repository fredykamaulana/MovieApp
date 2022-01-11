package com.miniapp.core.data.networkboundresource

import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.data.source.vo.ResourceState
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<ResourceState<ResultType>> = flow {
        emit(ResourceState.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(ResourceState.Loading())
            when (val apiResponse = createCall().first()) {
                is RemoteResult.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { ResourceState.Success(it) })
                }
                is RemoteResult.Empty -> {
                    emitAll(loadFromDB().map { ResourceState.Success(it) })
                }
                is RemoteResult.Error -> {
                    onFetchFailed()
                    emit(ResourceState.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { ResourceState.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<RemoteResult<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<ResourceState<ResultType>> = result
}