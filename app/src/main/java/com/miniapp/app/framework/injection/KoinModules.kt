package com.miniapp.app.framework.injection

import com.miniapp.app.BuildConfig
import com.miniapp.app.framework.api.GetWatchListApi
import com.miniapp.app.framework.source.GetWatchListRemoteSource
import com.miniapp.app.ui.login.LoginViewModel
import com.miniapp.app.ui.watchlist.WatchListViewModel
import com.miniapp.data.watchlist.WatchlistRepository
import com.miniapp.usecase.watchlist.GetWatchlistUseCase
import com.miniapp.usecase.watchlist.WatchlistUseCases
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun injectKoinModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        networkModule,
        apiModule,
        repositoryModule,
        usecaseModule,
        usecasesModule,
        viewModelModule
    )
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
            .baseUrl(BuildConfig.BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}

val apiModule: Module = module {
    factory {
        (get<Retrofit>()).create(GetWatchListApi::class.java)
    }
}

val repositoryModule: Module = module {
    factory {
        WatchlistRepository(GetWatchListRemoteSource(api = get()))
    }
}

val usecaseModule: Module = module {
    factory {
        GetWatchlistUseCase(repository = get())
    }
}

val usecasesModule: Module = module {
    factory {
        WatchlistUseCases(getWatchlistUseCases = get())
    }
}

val viewModelModule: Module = module {

    viewModel { WatchListViewModel(useCases = get()) }

    viewModel { LoginViewModel() }
}

val modules = listOf(
    networkModule,
    apiModule,
    repositoryModule,
    usecaseModule,
    usecasesModule,
    viewModelModule
)