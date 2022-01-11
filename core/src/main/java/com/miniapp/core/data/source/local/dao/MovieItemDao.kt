package com.miniapp.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miniapp.core.data.source.local.entity.MovieItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieItem(movies: List<MovieItemEntity>)

    @Query("SELECT * FROM movie_item")
    fun getAllMovieList(): Flow<List<MovieItemEntity>>

    @Query("SELECT * FROM movie_item WHERE category = 'popular'")
    fun getPopularMovieList(): Flow<List<MovieItemEntity>>

    @Query("SELECT * FROM movie_item WHERE category = 'now_playing'")
    fun getNowPlayingMovieList(): Flow<List<MovieItemEntity>>

    @Query("SELECT * FROM movie_item WHERE category = 'top_rated'")
    fun getTopRatedMovieList(): Flow<List<MovieItemEntity>>

    @Query("SELECT * FROM movie_item WHERE category = 'upcoming'")
    fun getUpcomingMovieList(): Flow<List<MovieItemEntity>>
}