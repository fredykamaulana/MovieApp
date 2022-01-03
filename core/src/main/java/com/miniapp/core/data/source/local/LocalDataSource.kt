package com.miniapp.core.data.source.local

import com.miniapp.core.data.source.local.dao.AppDao

class LocalDataSource(private val appDao: AppDao) {
    suspend fun getAllAppData(): List<String> {
        val listAppData = arrayListOf<String>()
        appDao.getAllAppData().map {
            listAppData.add(it.data)
        }

        return listAppData.toList()
    }
}