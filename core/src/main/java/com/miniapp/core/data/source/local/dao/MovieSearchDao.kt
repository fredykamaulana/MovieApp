package com.miniapp.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miniapp.core.data.source.local.entity.MovieItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSearchItem(movies: List<MovieItemEntity>)

    @Query("SELECT * FROM movie_item WHERE title LIKE :query AND category = :category")
    fun getMovieSearch(query: String, category: String = "search"): Flow<List<MovieItemEntity>>
}