package com.wepe.trydagger.ui.movies.component

import com.wepe.trydagger.di.scope.FragmentScope
import com.wepe.trydagger.ui.movies.list.MoviesFragment
import com.wepe.trydagger.ui.movies.module.MoviesModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [(MoviesModule::class)])
interface MoviesComponent : AndroidInjector<MoviesFragment> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MoviesFragment>
}