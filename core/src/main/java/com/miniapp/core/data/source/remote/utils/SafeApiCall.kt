package com.miniapp.core.data.source.remote.utils

import com.miniapp.core.data.source.remote.response.BaseDto
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class SafeApiCall {
    open suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): Flow<RemoteResult<T>> {
        return withContext(dispatcher){
            flow {
                try {
                    val result = apiCall.invoke()
                    if (result != null ) emit(RemoteResult.Success(result)) else emit(RemoteResult.Empty)
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is HttpException -> {
                            when (throwable.code()) {
                                in 400..451 -> parseHttpError(throwable)
                                in 500..599 -> error(HttpState.SERVER_ERROR, throwable.code(), "Server Error")
                                else -> error(HttpState.NOT_DEFINED, throwable.code(), "Undefined error")
                            }
                        }
                        is UnknownHostException -> error(HttpState.NO_CONNECTION, null, "No internet connection")
                        is SocketTimeoutException -> error(HttpState.TIMEOUT, null, "Slow connection")
                        is IOException -> error(HttpState.BAD_RESPONSE, null, throwable.message)
                        else -> error(HttpState.NOT_DEFINED, null, throwable.message)
                    }
                }
            }
        }
    }

    private fun error(cause: HttpState, code: Int?, errorMessage: String?): RemoteResult.Error =
        RemoteResult.Error(cause, code, errorMessage)


    private fun parseHttpError(throwable: HttpException): RemoteResult<Nothing> {
        return try {
            val errorBody = throwable.response()?.errorBody()?.string() ?: "Unknown HTTP error body"
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(BaseDto::class.java)
            val baseDto = jsonAdapter.fromJson(errorBody)
            val errorMessage = baseDto?.message ?: ""
            error(HttpState.CLIENT_ERROR, throwable.code(), errorMessage)
        } catch (exception: Exception) {
            error(HttpState.CLIENT_ERROR, throwable.code(), exception.localizedMessage)
        }
    }
}