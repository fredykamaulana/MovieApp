package com.miniapp.movielist.di

import com.miniapp.movielist.data.repository.MovieListRepositoryImpl
import com.miniapp.movielist.data.source.local.MovieListLocalDataSource
import com.miniapp.movielist.data.source.remote.MovieListRemoteDataSource
import com.miniapp.movielist.domain.repository.IMovieListRepository
import com.miniapp.movielist.domain.usecase.IMovieListUseCase
import com.miniapp.movielist.domain.usecase.MovieListInteractor
import com.miniapp.movielist.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectKoinModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(modules)
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
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)