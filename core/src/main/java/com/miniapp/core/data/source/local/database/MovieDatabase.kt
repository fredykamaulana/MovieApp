package com.miniapp.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miniapp.core.data.source.local.dao.MovieDetailDao
import com.miniapp.core.data.source.local.dao.MovieSearchDao
import com.miniapp.core.data.source.local.dao.MovieItemDao
import com.miniapp.core.data.source.local.entity.MovieDetailEntity
import com.miniapp.core.data.source.local.entity.MovieItemEntity
import com.miniapp.core.data.source.local.utils.Converters

const val dbVersion = 1
const val exportScheme = false

@Database(
    entities = [
        MovieItemEntity::class,
        MovieDetailEntity::class
    ], version = dbVersion, exportSchema = exportScheme
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieItemDao(): MovieItemDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun movieSearchDao(): MovieSearchDao
}