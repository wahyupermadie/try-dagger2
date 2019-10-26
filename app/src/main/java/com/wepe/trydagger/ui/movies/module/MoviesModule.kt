package com.wepe.trydagger.ui.movies.module

import android.content.Context
import com.google.gson.Gson
import com.wepe.trydagger.data.database.MoviesDao
import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.data.remote.MoviesRepositoryImpl
import com.wepe.trydagger.domain.MoviesDomain
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides
    @Singleton
    fun moviesRepository(apiService: ApiService, gson: Gson, moviesDao: MoviesDao, context: Context): MoviesRepositoryImpl
            = MoviesRepositoryImpl(apiService, gson, moviesDao, context)

    @Provides
    @Singleton
    fun moviesDomain(repository: MoviesRepositoryImpl): MoviesDomain = MoviesDomain(repository)
}