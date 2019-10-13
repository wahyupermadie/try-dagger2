package com.wepe.trydagger.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wepe.trydagger.di.viewmodel.ViewModelFactory
import com.wepe.trydagger.di.viewmodel.ViewModelKey
import com.wepe.trydagger.ui.movies.viewmodel.DetailMoviesVM
import com.wepe.trydagger.ui.movies.viewmodel.MoviesViewModel
import com.wepe.trydagger.ui.tv.viewmodel.TvShowDetailVM
import com.wepe.trydagger.ui.tv.viewmodel.TvShowViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun provideMoviesViewModel(moviesViewModel: MoviesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMoviesVM::class)
    abstract fun provideDetailMoviesViewModel(detailMoviesVM: DetailMoviesVM) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    abstract fun provideTvShowViewModel(tvShowVM: TvShowViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowDetailVM::class)
    abstract fun provideTvShowDetailViewModel(tvShowDetailVM: TvShowDetailVM) : ViewModel
}