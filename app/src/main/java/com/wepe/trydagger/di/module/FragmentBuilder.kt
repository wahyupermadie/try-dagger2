package com.wepe.trydagger.di.module

import com.wepe.trydagger.di.scope.FragmentScope
import com.wepe.trydagger.ui.movies.fragment.MoviesFragment
import com.wepe.trydagger.ui.movies.module.MoviesModule
import com.wepe.trydagger.ui.tv.fragment.TvShowFragment
import com.wepe.trydagger.ui.tv.module.TvShowModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [(MoviesModule::class)])
    internal abstract fun bindMoviesFragment(): MoviesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [(TvShowModule::class)])
    internal abstract fun bindTvShowFragment(): TvShowFragment
}