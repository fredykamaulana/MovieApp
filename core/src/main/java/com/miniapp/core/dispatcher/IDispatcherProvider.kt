package com.miniapp.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}