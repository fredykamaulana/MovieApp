package com.miniapp.data.util

sealed class RemoteResult<out T> {
    object Loading : RemoteResult<Nothing>()
    data class Success<out T>(val data: T) : RemoteResult<T>()
    data class Error(
        val cause: HttpResult = HttpResult.NOT_DEFINED,
        val code: Int? = null,
        val errorMessage: String? = null
    ) : RemoteResult<Nothing>()
}