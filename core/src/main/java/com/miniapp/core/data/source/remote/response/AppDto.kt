package com.miniapp.core.data.source.remote.response

import com.squareup.moshi.Json

data class AppDto(
    @field:Json(name = "data")
    val data: String
)
