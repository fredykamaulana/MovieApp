package com.miniapp.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.miniapp.core.data.source.local.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieFavouriteDao {
    @Query("SELECT * FROM movie_detail WHERE favourite = 1")
    fun getAllMovieFavourite(): Flow<List<MovieDetailEntity>>

    @Query("UPDATE movie_detail SET favourite = :fav WHERE id = :id")
    suspend fun setMovieAsFavourite(id: Int, fav: Boolean)
}