package com.miniapp.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miniapp.core.domain.usecase.AppDataUseCase
import kotlinx.coroutines.launch

class AppViewModel(private val appDataUseCase: AppDataUseCase): ViewModel() {
    suspend fun getAppData(){
        viewModelScope.launch {
            appDataUseCase.getAppData()
        }
    }
}