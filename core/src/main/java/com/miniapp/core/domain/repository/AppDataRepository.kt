package com.miniapp.core.domain.repository

interface AppDataRepository {
    suspend fun getAppData(): List<String>
}