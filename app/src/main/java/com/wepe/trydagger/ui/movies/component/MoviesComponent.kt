package com.wepe.trydagger.ui.movies.component

import com.wepe.trydagger.di.scope.FragmentScope
import com.wepe.trydagger.ui.movies.list.MoviesFragment
import com.wepe.trydagger.ui.movies.module.MoviesModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = [(MoviesModule::class)])
interface MoviesComponent : AndroidInjector<MoviesFragment>{
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MoviesFragment>()
}