package com.miniapp.movielist.di

import com.miniapp.core.data.source.local.database.MovieDatabase
import com.miniapp.core.dispatcher.DispatcherProviderImpl
import com.miniapp.core.dispatcher.IDispatcherProvider
import com.miniapp.movielist.data.repository.MovieListRepositoryImpl
import com.miniapp.movielist.data.source.local.MovieListLocalDataSource
import com.miniapp.movielist.data.source.remote.MovieListRemoteDataSource
import com.miniapp.core.data.source.remote.service.MovieListService
import com.miniapp.movielist.domain.repository.IMovieListRepository
import com.miniapp.movielist.domain.usecase.IMovieListUseCase
import com.miniapp.movielist.domain.usecase.MovieListInteractor
import com.miniapp.movielist.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectKoinModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(modules)
}

val daoModule: Module = module {
    single { get<MovieDatabase>().movieItemDao() }
}

val apiModule: Module = module {
    single { get<Retrofit>().create(MovieListService::class.java) }
}

val dispatcherModule: Module = module {
    single<IDispatcherProvider> { DispatcherProviderImpl() }
}

val dataSourceModule: Module = module {
    single { MovieListLocalDataSource(dao = get()) }
    single { MovieListRemoteDataSource(service = get()) }
}

val repositoryModule: Module = module {
    single<IMovieListRepository> {
        MovieListRepositoryImpl(
            dispatcher = get(),
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val useCaseModule: Module = module {
    factory<IMovieListUseCase> { MovieListInteractor(repository = get()) }
}

val viewModelModule: Module = module {
    viewModel { MovieListViewModel(useCase = get()) }
}

val modules = listOf(
    daoModule,
    apiModule,
    dataSourceModule,
    dispatcherModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)