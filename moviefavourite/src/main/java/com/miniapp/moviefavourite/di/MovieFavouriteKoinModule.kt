package com.miniapp.moviefavourite.di

import com.miniapp.moviefavourite.data.repository.MovieFavouriteRepositoryImpl
import com.miniapp.moviefavourite.data.source.MovieFavouriteLocalDataSource
import com.miniapp.moviefavourite.domain.repository.IMovieFavouriteRepository
import com.miniapp.moviefavourite.domain.usecase.IMovieFavouriteUseCase
import com.miniapp.moviefavourite.domain.usecase.MovieFavouriteInteractor
import com.miniapp.moviefavourite.presentation.viewmodel.MovieFavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectKoinModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(modules)
}

val dataSourceModule: Module = module {
    single { MovieFavouriteLocalDataSource(dao = get()) }
}

val repositoryModule: Module = module {
    single<IMovieFavouriteRepository> { MovieFavouriteRepositoryImpl(localDataSource = get()) }
}

val useCaseModule: Module = module {
    factory<IMovieFavouriteUseCase> { MovieFavouriteInteractor(repository = get()) }
}

val viewModelModule: Module = module {
    viewModel { MovieFavouriteViewModel(useCase = get()) }
}

val modules = listOf(
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)