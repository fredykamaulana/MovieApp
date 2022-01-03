package com.miniapp.core.domain.usecase

import com.miniapp.core.domain.repository.AppDataRepository

class AppInteractor(private val repository: AppDataRepository): AppDataUseCase {
    override suspend fun getAppData(): List<String> = repository.getAppData()

}