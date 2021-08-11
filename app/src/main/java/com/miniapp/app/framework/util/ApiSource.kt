package com.miniapp.app.framework.util

import com.miniapp.data.util.HttpResult
import com.miniapp.data.util.RemoteResult
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ApiSource {
    open suspend fun <T> getApiResult(apiCall: suspend () -> T): RemoteResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                RemoteResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                when (throwable) {
                    is HttpException -> {
                        val result = when (throwable.code()) {
                            in 400..451 -> parseHttpError(throwable)
                            in 500..599 -> error(
                                HttpResult.SERVER_ERROR,
                                throwable.code(),
                                "Server error"
                            )
                            else -> error(
                                HttpResult.NOT_DEFINED,
                                throwable.code(),
                                "Undefined error"
                            )
                        }
                        result
                    }
                    is UnknownHostException -> error(
                        HttpResult.NO_CONNECTION,
                        null,
                        "No internet connection"
                    )
                    is SocketTimeoutException -> error(HttpResult.TIMEOUT, null, "Slow connection")
                    is IOException -> error(HttpResult.BAD_RESPONSE, null, throwable.message)
                    else -> error(HttpResult.NOT_DEFINED, null, throwable.message)
                }
            }
        }
    }

    private fun error(cause: HttpResult, code: Int?, errorMessage: String?): RemoteResult.Error {
        return RemoteResult.Error(cause, code, errorMessage)
    }

    private fun parseHttpError(throwable: HttpException): RemoteResult<Nothing> {
        return try {
            val errorBody = throwable.response()?.errorBody()?.string() ?: "Unknown HTTP error body"
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(Object::class.java)
            val errorMessage = adapter.fromJson(errorBody)
            error(HttpResult.CLIENT_ERROR, throwable.code(), errorMessage.toString())
        } catch (exception: Exception) {
            error(HttpResult.CLIENT_ERROR, throwable.code(), exception.localizedMessage)
        }
    }
}