package com.miniapp.moviesearch.di

import com.miniapp.moviesearch.data.repository.MovieSearchRepositoryImpl
import com.miniapp.moviesearch.data.source.remote.MovieSearchRemoteDataSource
import com.miniapp.moviesearch.domain.repository.IMovieSearchRepository
import com.miniapp.moviesearch.domain.usecase.IMovieSearchUseCase
import com.miniapp.moviesearch.domain.usecase.MovieSearchInteractor
import com.miniapp.moviesearch.presentation.viewmodel.MovieSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectKoinModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(modules)
}

val dataSourceModule: Module = module {
    single { MovieSearchRemoteDataSource(service = get()) }
}

val repositoryModule: Module = module {
    single<IMovieSearchRepository> {
        MovieSearchRepositoryImpl(
            dispatcher = get(),
            remoteDataSource = get()
        )
    }
}

val useCaseModule: Module = module {
    factory<IMovieSearchUseCase> { MovieSearchInteractor(repository = get()) }
}

val viewModelModule: Module = module {
    viewModel { MovieSearchViewModel(useCase = get()) }
}

val modules = listOf(
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)