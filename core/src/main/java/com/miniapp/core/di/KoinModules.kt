package com.miniapp.core.di


import androidx.room.Room
import com.miniapp.core.data.source.local.database.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun injectKoinModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(modules)
}

val networkModule: Module = module {

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)

        Retrofit.Builder()
            .baseUrl("BuildConfig.BASE_URL")
            .client(clientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .build()
    }
}

val databaseModule: Module = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "AppDatabase.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val apiModule: Module = module {

}

val repositoryModule: Module = module {

}

val useCaseModule: Module = module {

}

val useCasesModule: Module = module {

}

val viewModelModule: Module = module {

}

val modules = listOf(
    networkModule,
    databaseModule,
    apiModule,
    repositoryModule,
    useCaseModule,
    useCasesModule,
    viewModelModule
)