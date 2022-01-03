package com.miniapp.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miniapp.core.data.source.local.dao.AppDao
import com.miniapp.core.data.source.local.entity.AppEntity

const val dbVersion = 1
const val exportScheme = false

@Database(entities = [AppEntity::class], version = dbVersion, exportSchema = exportScheme)
abstract class AppDatabase: RoomDatabase() {

    abstract fun appDao(): AppDao

}