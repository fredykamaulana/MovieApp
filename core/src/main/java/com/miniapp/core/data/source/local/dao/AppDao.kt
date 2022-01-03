package com.miniapp.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miniapp.core.data.source.local.entity.AppEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppData(appData: AppEntity)

    @Query("SELECT * FROM app_table")
    suspend fun getAllAppData(): List<AppEntity>

}