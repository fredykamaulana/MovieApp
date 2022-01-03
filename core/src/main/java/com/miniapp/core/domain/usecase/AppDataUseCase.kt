package com.miniapp.core.domain.usecase

interface AppDataUseCase {
    suspend fun getAppData(): List<String>
}