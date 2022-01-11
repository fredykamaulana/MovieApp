package com.miniapp.core

import android.app.Application
import com.miniapp.core.di.databaseModule
import com.miniapp.core.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MiniApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initTimber(){
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initKoin(){
        startKoin {
            androidContext(this@MiniApp)
            modules(
                networkModule,
                databaseModule,
            )
        }
    }
}