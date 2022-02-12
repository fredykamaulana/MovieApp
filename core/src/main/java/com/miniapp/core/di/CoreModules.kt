package com.miniapp.core.di


import androidx.room.Room
import com.miniapp.core.BuildConfig
import com.miniapp.core.data.source.local.database.MovieDatabase
import com.miniapp.core.data.source.remote.service.MovieDetailService
import com.miniapp.core.data.source.remote.service.MovieListService
import com.miniapp.core.data.source.remote.service.MovieSearchService
import com.miniapp.core.dispatcher.DispatcherProviderImpl
import com.miniapp.core.dispatcher.IDispatcherProvider
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val host ="api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(host, "sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
            .add(host, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(host, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(host, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()

        if (BuildConfig.DEBUG){
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .certificatePinner(certificatePinner)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
        } else {
            OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .build()
    }
}

val databaseModule: Module = module {
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movieApp".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "MovieDatabase.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val daoModule: Module = module {
    single { get<MovieDatabase>().movieItemDao() }
    single { get<MovieDatabase>().movieDetailDao() }
    single { get<MovieDatabase>().movieSearchDao() }
    single { get<MovieDatabase>().movieFavouriteDao() }
}

val apiModule: Module = module {
    single { get<Retrofit>().create(MovieListService::class.java) }
    single { get<Retrofit>().create(MovieDetailService::class.java) }
    single { get<Retrofit>().create(MovieSearchService::class.java) }
}

val dispatcherModule: Module = module {
    factory<IDispatcherProvider> { DispatcherProviderImpl() }
}

val modules = listOf(
    networkModule,
    databaseModule,
    daoModule,
    apiModule,
    dispatcherModule
)