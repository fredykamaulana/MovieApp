package com.miniapp.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miniapp.core.data.source.local.dao.MovieItemDao
import com.miniapp.core.data.source.local.entity.MovieItemEntity

const val dbVersion = 1
const val exportScheme = false

@Database(entities = [MovieItemEntity::class], version = dbVersion, exportSchema = exportScheme)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieItemDao(): MovieItemDao

}