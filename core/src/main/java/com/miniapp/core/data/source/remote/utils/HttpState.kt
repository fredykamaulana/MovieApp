package com.miniapp.core.data.source.remote.utils

enum class HttpState {
    NO_CONNECTION,
    TIMEOUT,
    CLIENT_ERROR,
    BAD_RESPONSE,
    SERVER_ERROR,
    NOT_DEFINED,
}