package com.miniapp.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MiniApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MiniApp)
            modules(com.miniapp.app.framework.injection.modules)
        }
    }
}