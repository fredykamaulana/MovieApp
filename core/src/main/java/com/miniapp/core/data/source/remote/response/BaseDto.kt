package com.miniapp.core.data.source.remote.response

import com.squareup.moshi.Json

data class BaseDto(
    @field:Json(name = "status_code")
    val code: Int,
    @field:Json(name = "status_message")
    val message: String,
    @field:Json(name = "success")
    val success: Boolean
)