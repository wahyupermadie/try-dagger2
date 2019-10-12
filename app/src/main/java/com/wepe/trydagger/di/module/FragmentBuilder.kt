package com.wepe.trydagger.di.module

import com.wepe.trydagger.di.scope.FragmentScope
import com.wepe.trydagger.ui.movies.fragment.MoviesFragment
import com.wepe.trydagger.ui.movies.module.MoviesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [(MoviesModule::class)])
    internal abstract fun bindMoviesFragment(): MoviesFragment
}