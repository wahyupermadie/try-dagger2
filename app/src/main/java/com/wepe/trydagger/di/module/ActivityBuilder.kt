package com.wepe.trydagger.di.module

import com.wepe.trydagger.di.scope.ActivityScope
import com.wepe.trydagger.ui.movies.detail.DetailMovieActivity
import com.wepe.trydagger.ui.movies.module.DetailMoviesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(DetailMoviesModule::class)])
    internal abstract fun bindDetailMoviesActivity(): DetailMovieActivity
}