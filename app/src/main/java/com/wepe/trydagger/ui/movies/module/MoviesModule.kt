package com.wepe.trydagger.ui.movies.module

import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.data.remote.MoviesRepositoryImpl
import com.wepe.trydagger.di.scope.FragmentScope
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.utils.CoroutinesAppProvider
import com.wepe.trydagger.utils.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides
    fun moviesRepository(apiService: ApiService): MoviesRepositoryImpl = MoviesRepositoryImpl(apiService)

    @Provides
    fun moviesDomain(repository: MoviesRepositoryImpl): MoviesDomain = MoviesDomain(repository)
}