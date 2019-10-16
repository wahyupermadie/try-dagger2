package com.wepe.trydagger.di.module

import com.wepe.trydagger.di.scope.ActivityScope
import com.wepe.trydagger.ui.MainActivity
import com.wepe.trydagger.ui.movies.detail.DetailMovieActivity
import com.wepe.trydagger.ui.tv.detail.DetailTvShowActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun bindDetailMoviesActivity(): DetailMovieActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun bindDetailTvShowActivity(): DetailTvShowActivity
}