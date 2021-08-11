package com.miniapp.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(): ViewModel() {

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> get() =  _loginStatus

    fun loginStatus(b: Boolean){
        _loginStatus.value = false
        viewModelScope.launch {
            delay(1000)
            _loginStatus.value = true
        }
    }
}