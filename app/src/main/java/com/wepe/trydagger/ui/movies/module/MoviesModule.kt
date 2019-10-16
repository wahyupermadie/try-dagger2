package com.wepe.trydagger.ui.movies.module

import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.data.remote.MoviesRepositoryImpl
import com.wepe.trydagger.di.scope.FragmentScope
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.ui.movies.fragment.MoviesContract
import com.wepe.trydagger.ui.movies.fragment.MoviesFragment
import com.wepe.trydagger.ui.movies.fragment.MoviesPresenter
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {
    @Provides
    @FragmentScope
    fun moviesRepository(apiService: ApiService): MoviesRepositoryImpl = MoviesRepositoryImpl(apiService)

    @Provides
    @FragmentScope
    fun moviesDomain(repository: MoviesRepositoryImpl): MoviesDomain = MoviesDomain(repository)

    @Provides
    @FragmentScope
    internal fun provideFragment(fragment: MoviesFragment): MoviesContract.View {
        return fragment
    }

    @Provides
    @FragmentScope
    fun moviesPresenter(moviesDomain: MoviesDomain): MoviesPresenter = MoviesPresenter(moviesDomain)

}