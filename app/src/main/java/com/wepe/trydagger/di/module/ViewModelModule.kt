package com.wepe.trydagger.di.module

import androidx.lifecycle.ViewModel
import com.wepe.trydagger.di.viewmodel.ViewModelKey
import com.wepe.trydagger.ui.movies.viewmodel.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun provideMoviesViewModel(moviesViewModel: MoviesViewModel) : ViewModel
}