package com.miniapp.core.data.source.remote.response

import com.squareup.moshi.Json

data class MovieListDto(
    @field:Json(name = "page")
    val page: Int? = null,

    @field:Json(name = "total_pages")
    val totalPages: Int? = null,

    @field:Json(name = "results")
    val results: List<MovieItemDto>? = null,

    @field:Json(name = "total_results")
    val totalResults: Int? = null
) {
    data class MovieItemDto(

        @field:Json(name = "original_title")
        val originalTitle: String? = null,

        @field:Json(name = "title")
        val title: String? = null,

        @field:Json(name = "poster_path")
        val posterPath: String? = null,

        @field:Json(name = "release_date")
        val releaseDate: String? = null,

        @field:Json(name = "vote_average")
        val voteAverage: Float? = null,

        @field:Json(name = "id")
        val id: Int? = null,
    )
}