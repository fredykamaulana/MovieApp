package com.miniapp.moviedetail.di

import com.miniapp.moviedetail.data.repository.MovieDetailRepositoryImpl
import com.miniapp.moviedetail.data.source.local.MovieDetailLocalDataSource
import com.miniapp.moviedetail.data.source.remote.MovieDetailRemoteDataSource
import com.miniapp.moviedetail.domain.repository.IMovieDetailRepository
import com.miniapp.moviedetail.domain.usecase.IMovieDetailUseCase
import com.miniapp.moviedetail.domain.usecase.MovieDetailInteractor
import com.miniapp.moviedetail.presentation.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectKoinModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(modules)
}

val dataSourceModule: Module = module {
    single { MovieDetailLocalDataSource(dao = get()) }
    single { MovieDetailRemoteDataSource(service = get()) }
}

val repositoryModule: Module = module {
    single<IMovieDetailRepository> {
        MovieDetailRepositoryImpl(
            dispatcher = get(),
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val useCaseModule: Module = module {
    factory<IMovieDetailUseCase> { MovieDetailInteractor(repository = get()) }
}

val viewModelModule: Module = module {
    viewModel {parameters ->  MovieDetailViewModel(parameters.get<Int>() as Int, useCase = get()) }
}

val modules = listOf(
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)